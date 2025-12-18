package com.inzeph.EmployeeManagement.Repository;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.UploadedFiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepo extends JpaRepository<UploadedFiles, Long> {
    List<UploadedFiles> findByModuleAndModuleID(String module, String moduleID);
}
