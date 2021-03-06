package com.codepulse.visitlogService.Repository;

import com.codepulse.visitlogService.model.TbClientMst;
import com.codepulse.visitlogService.repository.ClientMstRepository;
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
    private ClientMstRepository clientRepository;

    @Test
    public void AddShop(String cltCd, String cltNm, String ceo, String tel, String email, String bizCd) {
        TbClientMst client = new TbClientMst();

        client.setCltCd(cltCd);
        client.setCltNm(cltNm);
        client.setCeo(ceo);
        client.setTel(tel);
        client.setEmail(email);
        client.setBizCd(bizCd);

        clientRepository.save(client);
    }

    @Test
    public TbClientMst GetClient(String cltCd) {
        boolean isResult = clientRepository.findById(cltCd).isPresent();

        /*
        if (!isResult) {
            log.error(cltCd + " not found data");
            return null;
        }
        */
        return clientRepository.findByCltCd(cltCd);
    }
}
