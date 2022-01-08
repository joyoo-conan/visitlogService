package com.codepulse.visitlogService.service;

import com.codepulse.visitlogService.model.TbClientMst;
import com.codepulse.visitlogService.repository.ClientMstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientMstService {
    private final ClientMstRepository clientMstRepository;

    @Autowired
    public ClientMstService(ClientMstRepository clientMstRepository) { this.clientMstRepository = clientMstRepository; }

    public List<TbClientMst> getAllClient() { return clientMstRepository.findAll(); }

    public TbClientMst getClientById(String cltCd) { return clientMstRepository.findByCltCd(cltCd); }

    public TbClientMst addClient(TbClientMst client) { return clientMstRepository.save(client); };

    public void removeClientById(String cltCd) { clientMstRepository.deleteById(cltCd); }
}
