package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.ClientServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.CustomerServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inzeph.EmployeeManagement.Model.Client;
import com.inzeph.EmployeeManagement.Model.Customer;
import com.inzeph.EmployeeManagement.Utils.Util;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientServiceInterface service;

    @Autowired
    CustomerServiceInterface customerService;

    @GetMapping("/customer")
    public ResponseEntity<Object> getAllClientCustomer() {
        List<Client> clients = service.getAllClient();
        List<Customer> customers = customerService.getAllCustomer();
        Map<String, Object> result;
        if (clients.isEmpty()) {
            return Util.generateResponse("Client was not found", HttpStatus.NO_CONTENT, null);
        } else if (customers.isEmpty()) {
            return Util.generateResponse("Customer was not found", HttpStatus.NO_CONTENT, null);
        } else {
            result = new HashMap<>();
            result.put("client", clients);
            result.put("customer", customers);
        }
        return Util.generateResponse("Successfully retrieved Client and Customer!", HttpStatus.OK, result);
    }

    @GetMapping
    public ResponseEntity<Object> getAllClient() {
        List<Client> clients = service.getAllClient();
        if (clients.isEmpty()) {
            return Util.generateResponse("Client was not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved Client!", HttpStatus.OK, clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable long id) {
        Client client = service.getClientById(id);
        if (client == null) {
            return Util.generateResponse("Client " + id + " not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved client!", HttpStatus.OK, client);
    }

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        return Util.generateResponse("Successfully created a client", HttpStatus.CREATED, service.addClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody Client updateClient) {
        Client client = service.getClientById(id);
        if (client == null) {
            return Util.generateResponse("Client " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(updateClient, client);
        client.setId(id);
        return Util.generateResponse("successfully Updated client", HttpStatus.OK, service.updateClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        Client client = service.getClientById(id);
        if (client == null) {
            return Util.generateResponse("Client " + id + " was not found", HttpStatus.BAD_REQUEST, null);
        }
        service.deleteClient(client);
        return Util.generateResponse("Successfully deleted the client!", HttpStatus.OK, id);
    }
}