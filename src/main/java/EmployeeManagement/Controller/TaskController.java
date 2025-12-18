package com.inzeph.EmployeeManagement.Controller;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Task;
import com.inzeph.EmployeeManagement.ServiceInterface.TaskServiceInterface;
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
@RequestMapping("/task")
public class TaskController {
    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getAllTasks() {
        List<Task> task = service.getAllTask();
        if (task.isEmpty()) {
            return Util.generateResponse("No task found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved tasks!", HttpStatus.OK, task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable long id) {
        Task task = service.getById(id);
        if (task == null) {
            return Util.generateResponse("Task " + id + " not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved task!", HttpStatus.OK, task);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Object> getTaskByName(@PathVariable String name) {
        Task task = service.getByName(name);
        if (task == null) {
            return Util.generateResponse("Task " + name + " not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved task!", HttpStatus.OK, task);
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody Task task) throws Exception {
        if (service.getByField("employee", task.getEmployee())) {
            return Util.generateResponse("Employee " + task.getEmployee() + " was Not Found", HttpStatus.BAD_REQUEST,
                    null);
        }
        return Util.generateResponse("Successfully created task!", HttpStatus.CREATED,
                service.addTasks(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody Task updateTask) {
        Task task = service.getById(id);
        if (task == null) {
            return Util.generateResponse("Task " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(updateTask, task);
        task.setId(id);
        return Util.generateResponse("successfully Updated task", HttpStatus.OK, service.updateTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        Task task = service.getById(id);
        if (task == null) {
            return Util.generateResponse("task " + id + " was not found", HttpStatus.BAD_REQUEST, null);
        }
        service.deleteTask(task);
        return Util.generateResponse("Successfully deleted the task!", HttpStatus.OK, id);
    }

}
