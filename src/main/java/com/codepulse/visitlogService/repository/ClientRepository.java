package com.codepulse.visitlogService.Repository;

import com.codepulse.visitlogService.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    //Get a list of all clients
    List<Client> findAll();

    //Get a list of use_yn enable clients
    List<Client> findByUseYn(int useYn);

    //Get the specified client information
    Client findByCltCd(String cltCd);

    //Store shop information
    Client save(Client client);

    //Delete the specified client information
    @Override
    void deleteById(String cltCd);
}
