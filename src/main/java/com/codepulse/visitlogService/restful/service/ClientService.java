package com.codepulse.visitlogService.restful.service;

import com.codepulse.visitlogService.restful.dto.ClientDto;
import com.codepulse.visitlogService.restful.dto.req.ClientReqDto;
import com.codepulse.visitlogService.restful.dto.res.ClientResDto;
import com.codepulse.visitlogService.restful.model.Client;
import com.codepulse.visitlogService.restful.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public List<ClientDto> getAllClient() {
        List<Client> clients = clientRepository.findAll();

        return clients
                .stream()
                .map(x -> modelMapper.map(x, ClientDto.class))
                .collect(Collectors.toList());
    }

    public ClientDto getClientById(String id) {
        Client client = clientRepository.findById(id).get();

        return modelMapper.map(client, ClientDto.class);
    }

    public ClientResDto regClient(ClientReqDto client) {
        Client newClient = modelMapper.map(client, Client.class);
        return modelMapper.map(clientRepository.save(newClient), ClientResDto.class);
    }

    public ClientResDto chgClient(ClientReqDto client) {
        Client newClient = modelMapper.map(client, Client.class);
        return modelMapper.map(clientRepository.save(newClient), ClientResDto.class);
    }
}
