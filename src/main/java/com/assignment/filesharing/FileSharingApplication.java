package com.assignment.filesharing;


import com.assignment.filesharing.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.assignment.filesharing.*")
@EnableConfigurationProperties({
		FileStorageProperties.class
})

public class FileSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSharingApplication.class, args);
	}
}
