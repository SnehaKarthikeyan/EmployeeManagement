package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Client;

public interface ClientServiceInterface {
    Client getClientById(Long id);

    List<Client> getAllClient();

    Client addClient(Client client);

    Client updateClient(Client client);

    void deleteClient(Client client);
}
