package com.codepulse.visitlogService.restful.controller;

import com.codepulse.visitlogService.restful.dto.ClientUserDto;
import com.codepulse.visitlogService.restful.model.ClientUser;
import com.codepulse.visitlogService.restful.repository.ClientUserRepository;
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
public class ClientUserController {

    private final ClientUserRepository clientUserRepository;
    private final ModelMapper modelMapper;

    private List<ClientUserDto> findClientUserByCltCd(@RequestParam String cltCd) {
        List<ClientUser> clientUsers = clientUserRepository.findByCltCd(cltCd);

        return clientUsers
                .stream()
                .map(x -> modelMapper.map(clientUsers, ClientUserDto.class))
                .collect(Collectors.toList());
    }
}
