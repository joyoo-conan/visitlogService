package com.codepulse.visitlogService.repository;

import com.codepulse.visitlogService.model.TbClientMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientMstRespository extends JpaRepository<TbClientMst, String> {
    //Get a list of all clients
    List<TbClientMst> findAll();

    //Get a list of use_yn enable clients
    List<TbClientMst> findByUseYn(int useYn);

    //Get the specified client information
    TbClientMst findByCltCd(String cltCd);

    //Store shop information
    TbClientMst save(TbClientMst clientMst);

    //Delete the specified client information
    @Override
    void deleteById(String cltCd);
}
