package com.codepulse.visitlogService.restful.repository;

import com.codepulse.visitlogService.restful.model.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, String> {

    public List<ClientUser> findByCltCd(String cltCd);
}
