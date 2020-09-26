package com.assignment.filesharing.service;

import com.assignment.filesharing.repository.FileSharingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FileStorageServiceTest {
    @Mock
    FileSharingRepository fileSharingRepository;

    @Mock
    Path fileStorageLocation;

    @Mock
    FileStorageService fileStorageService;

    @Test
    //Upload file success
    public void StoreFileSuccess () {
        //input
/*
        int userId = 1;
        Path path = mock(Path.class);
        path.startsWith("C:/Users/tptmm/UploadFolder");
        doReturn(path).when(Paths.get(any()));
        //mock
        MockMultipartFile inputFile = new MockMultipartFile("data", "ASSISSMENT1.docx", "text/plain", "some xml".getBytes());
        when(fileSharingRepository.findDBFilesByName(inputFile.getName(), userId)).thenReturn(null);
        ArgumentCaptor<DBFile> dbFileArgumentCaptor = ArgumentCaptor.forClass(DBFile.class);
        when(fileStorageLocation.resolve(inputFile.getName())).thenReturn(path);
        fileStorageService.storeFile(inputFile, userId);
        verify(fileSharingRepository).save(dbFileArgumentCaptor.capture());

        //verifyData(Expect, Actual)
        assertEquals(inputFile.getName(), dbFileArgumentCaptor.getValue().getFileName());
        assertEquals(inputFile.getContentType(), dbFileArgumentCaptor.getValue().getFileType());
        assertEquals(userId, dbFileArgumentCaptor.getValue().getUserId());
*/

    }
}
