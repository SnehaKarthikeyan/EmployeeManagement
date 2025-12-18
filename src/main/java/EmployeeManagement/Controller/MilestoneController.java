package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.Model.Milestone;
import com.inzeph.EmployeeManagement.ServiceInterface.MilestoneServiceInterface;
import com.inzeph.EmployeeManagement.Utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/milestone")
public class MilestoneController {
    Logger logger = LoggerFactory.getLogger(MilestoneController.class);
    @Autowired
    MilestoneServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getAllMilestones() {
        List<Milestone> milestones = service.getAllMilestones();
        if (milestones.isEmpty()) {
            return Util.generateResponse("No milestones found", HttpStatus.NO_CONTENT,
                    null);
        }
        return Util.generateResponse("Successfully retrieved milestones!", HttpStatus.OK, milestones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMilestoneById(@PathVariable("id") long id) {
        Milestone milestone = service.getById(id);
        if (milestone == null) {
            return Util.generateResponse("Milestone " + id + " not found",
                    HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved milestone!", HttpStatus.OK, milestone);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getMilestoneByName(@PathVariable("name") String name) {
        Milestone milestone = service.getByName(name);
        if (milestone == null) {
            return Util.generateResponse("Milestone " + name + " not found",
                    HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved milestone!", HttpStatus.OK, milestone);
    }

    @PostMapping
    public ResponseEntity<Object> createMilestone(@RequestBody Milestone milestone) throws Exception {
        if (service.getByField("employee", milestone.getEmployee())) {
            return Util.generateResponse("Employee " + milestone.getEmployee() + " was Not Found", HttpStatus.BAD_REQUEST,
                    null);
        }
        return Util.generateResponse("Successfully created milestone!", HttpStatus.CREATED, service.saveMilestone(milestone));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMilestone(@PathVariable("id") Long id, @RequestBody Milestone updateMilestone) {
        Milestone milestone = service.getById(id);
        if (milestone == null) {
            return Util.generateResponse("Milestone " + id + " was not found ",
                    HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(updateMilestone, milestone);
        milestone.setId(id);
        return Util.generateResponse("successfully Updated milestone", HttpStatus.OK, service.updateMilestone(milestone));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMilestone(@PathVariable("id") Long id) {
        Milestone milestone = service.getById(id);
        if (milestone == null) {
            return Util.generateResponse("Milestone " + id + " was not found",
                    HttpStatus.BAD_REQUEST, null);
        }
        service.deleteMilestone(milestone);
        return Util.generateResponse("Successfully deleted the milestone!", HttpStatus.OK, id);
    }
}
