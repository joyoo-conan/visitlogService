package com.codepulse.visitlogService.Service;

import com.codepulse.visitlogService.Entity.Client;
import com.codepulse.visitlogService.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository shopRepository) {
        this.clientRepository = shopRepository;
    }

    public List<Client> getAllClient() { return clientRepository.findAll(); }

    public Client getClientById(String cltCd) { return clientRepository.findByCltCd(cltCd); }

    public Client addClient(Client client) { return clientRepository.save(client); };

    public void removeClientById(String cltCd) { clientRepository.deleteById(cltCd); }
}
