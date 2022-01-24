package com.codepulse.visitlogService.Repository;

import com.codepulse.visitlogService.restful.model.Client;
import com.codepulse.visitlogService.restful.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@Commit
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestShopRepository {
    @Autowired
    private ClientRepository clientRepository;


    /**
    @Test
    public Client GetClient(String cltCd) {
        boolean isResult = clientRepository.findById(cltCd).isPresent();

        return clientRepository.findByCltCd(cltCd);
    }
    */
}
