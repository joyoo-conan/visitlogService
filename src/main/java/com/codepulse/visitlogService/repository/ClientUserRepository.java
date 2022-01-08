package com.codepulse.visitlogService.repository;

import com.codepulse.visitlogService.model.TbClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientUserRepository extends JpaRepository<TbClientUser, String> {

}
