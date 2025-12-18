package com.inzeph.EmployeeManagement.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.inzeph.EmployeeManagement.Model.UploadedFiles;
import com.inzeph.EmployeeManagement.Service.FilesService;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/files")
public class FilesController {
    Logger logger = LoggerFactory.getLogger(FilesController.class);

    @Autowired
    private FilesService service;

    @PostMapping("/uploadFile")
    public Object uploadFile(@RequestParam String moduleName, @RequestParam String moduleID,
            @RequestParam("file") MultipartFile file) {
        // String fileName = service.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files")
                .path(file.getOriginalFilename())
                .toUriString();
        return Util.generateResponse("File Added Sucessdully", HttpStatus.OK,
                service.storeFile(file, moduleName, moduleID, fileDownloadUri));
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Object> uploadMultipleFiles(@RequestParam String moduleName, @RequestParam String moduleID,
            @RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(moduleName, moduleID, file))
                .collect(Collectors.toList());
    }

    @GetMapping("/{module}/{moduleID}")
    public ResponseEntity<Object> getModuleFiles(@PathVariable String module, @PathVariable String moduleID) {
        List<UploadedFiles> response = service.getFiles(module.toLowerCase(), moduleID);
        if (response.size() == 0) {
            return Util.generateResponse("No Files Available", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Files Fetched Successfully!!!", HttpStatus.OK, response);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = service.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
