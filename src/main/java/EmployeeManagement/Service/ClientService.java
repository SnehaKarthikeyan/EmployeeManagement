package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.ClientRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClientService implements ClientServiceInterface {
    Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    ClientRepo repo;

    @Override
    public List<Client> getAllClient() {
        return repo.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> client = repo.findById(id);
        return client.isPresent() ? client.get() : null;
    }

    @Override
    public Client addClient(Client client) {
        return repo.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return repo.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        repo.delete(client);
    }
}
