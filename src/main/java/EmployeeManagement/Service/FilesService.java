package com.inzeph.EmployeeManagement.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.inzeph.EmployeeManagement.Exception.FileStorageException;
import com.inzeph.EmployeeManagement.Exception.MyFileNotFoundException;
import com.inzeph.EmployeeManagement.Model.UploadedFiles;
import com.inzeph.EmployeeManagement.Repository.FilesRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.FilesServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FilesService implements FilesServiceInterface {
    Logger logger = LoggerFactory.getLogger(FilesService.class);

    private final Path fileStorageLocation;

    @Autowired
    FilesRepo repo;

    @Autowired
    public FilesService() {
        this.fileStorageLocation = Paths.get("E:\\Employee-management").toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }
    }

    public UploadedFiles storeFile(MultipartFile file, String moduleName, String moduleID, String fileDownloadUri) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return repo.save(new UploadedFiles(moduleName, moduleID, fileName, file.getContentType(),
                    String.valueOf(file.getSize()), fileDownloadUri));
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public List<UploadedFiles> getFiles(String module, String moduleID) {
        return repo.findByModuleAndModuleID(module, moduleID);
    }
}
