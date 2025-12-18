package com.inzeph.EmployeeManagement.Controller;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Project;
import com.inzeph.EmployeeManagement.ServiceInterface.ProjectServiceInterface;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/{project}")
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    ProjectServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getAllProjects() {
        List<Project> project = service.getAllProjects();
        if (project.isEmpty()) {
            return Util.generateResponse("No project found", HttpStatus.NO_CONTENT,
                    null);
        }
        return Util.generateResponse("Successfully retrieved projects!",
                HttpStatus.OK, project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable long id) {
        Project project = service.getById(id);
        if (project == null) {
            return Util.generateResponse("Project " + id + " not found",
                    HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved project!",
                HttpStatus.OK, project);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getProjectByName(@PathVariable String name) {
        Project project = service.getByName(name);
        if (project == null) {
            return Util.generateResponse("Project " + name + " not found",
                    HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved project!",
                HttpStatus.OK, project);
    }

    @PostMapping
    public ResponseEntity<Object> createProject(@RequestBody Project project) throws Exception {
        if (service.getByField("employee", project.getEmployee())) {
            return Util.generateResponse("Employee " + project.getEmployee() + " was Not Found", HttpStatus.BAD_REQUEST,
                    null);
        }
        return Util.generateResponse("Successfully created project!",
                HttpStatus.CREATED,
                service.saveProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable Long id,
            @RequestBody Project updateProject) {
        Project project = service.getById(id);
        if (project == null) {
            return Util.generateResponse("Project " + id + " was not found ",
                    HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(updateProject, project);
        project.setId(id);
        return Util.generateResponse("successfully Updated project", HttpStatus.OK,
                service.updateProject(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long id) {
        Project project = service.getById(id);
        if (project == null) {
            return Util.generateResponse("Project " + id + " was not found",
                    HttpStatus.BAD_REQUEST, null);
        }
        service.deleteProject(project);
        return Util.generateResponse("Successfully deleted the project!",
                HttpStatus.OK, id);
    }
}
