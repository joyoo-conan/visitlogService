package com.codepulse.visitlogService.restful.service;

import com.codepulse.visitlogService.common.base.CustomMessage;
import com.codepulse.visitlogService.common.exception.CDuplicatedDataException;
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

    /**
     * 모든 CLIENT 정보 조회
     * @return
     */
    public List<ClientDto> getAllClient() {
        List<Client> clients = clientRepository.findAll();

        return clients
                .stream()
                .map(x -> modelMapper.map(x, ClientDto.class))
                .collect(Collectors.toList());
    }

    /**
     * 지정 CLIENT 정보 조회
     * @param id
     * @return
     */
    public ClientDto getClientById(String id) {
        Client client = clientRepository.findById(id).get();
        return modelMapper.map(client, ClientDto.class);
    }

    /**
     * 신규 CLIENT 등록
     * @param clientReq
     * @return
     */
    public ClientResDto regClient(ClientReqDto clientReq) {

        Client client = clientRepository.findById(clientReq.getCltCd()).get();
        if (client != null) throw new CDuplicatedDataException(CustomMessage.CLIENT_DUPLICATED.getMsg());

        client = modelMapper.map(clientReq, Client.class);
        return modelMapper.map(clientRepository.save(client), ClientResDto.class);
    }

    /**
     * CLIENT 정보 수정
     * @param client
     * @return
     */
    public ClientResDto chgClient(ClientReqDto client) {
        Client newClient = modelMapper.map(client, Client.class);
        return modelMapper.map(clientRepository.save(newClient), ClientResDto.class);
    }
}
