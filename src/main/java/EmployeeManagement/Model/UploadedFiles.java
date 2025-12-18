package com.inzeph.EmployeeManagement.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadedFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String module;
    private String moduleID;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String fileDownloadUri;

    public UploadedFiles(String module, String moduleID, String fileName, String fileType, String fileSize,
            String fileDownloadUri) {
        this.module = module;
        this.moduleID = moduleID;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileDownloadUri = fileDownloadUri;
    }
}
