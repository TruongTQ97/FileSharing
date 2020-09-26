package com.assignment.filesharing.service;

import com.assignment.filesharing.exception.FileStorageException;
import com.assignment.filesharing.exception.MyFileSharingException;
import com.assignment.filesharing.model.DBFile;
import com.assignment.filesharing.property.FileStorageProperties;
import com.assignment.filesharing.repository.FileSharingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    private FileSharingRepository fSRepository;


    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties, FileSharingRepository fSRepository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.fSRepository = fSRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void storeFile(MultipartFile file, int userId) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            DBFile dbFile = fSRepository.findDBFilesByName(fileName, userId);

            if(dbFile == null){
                DBFile newDBFile = new DBFile(fileName, file.getContentType(), userId);
                fSRepository.save(newDBFile);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName, int userId) {
        try {
            DBFile newDBFile = fSRepository.findDBFilesByName(fileName, userId);
            if (newDBFile != null) {
                Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists()) {
                    return resource;
                } else {
                    throw new MyFileSharingException("File not found " + fileName);
                }
            }
            else{
                throw new MyFileSharingException("Access denied! " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileSharingException("File not found " + fileName, ex);
        }
    }
}