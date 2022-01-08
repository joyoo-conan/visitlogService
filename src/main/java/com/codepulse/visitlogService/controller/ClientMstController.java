package com.codepulse.visitlogService.controller;

import com.codepulse.visitlogService.Common.AES256Chiper;
import com.codepulse.visitlogService.Common.CommLib;
import com.codepulse.visitlogService.model.Response;
import com.codepulse.visitlogService.model.TbClientMst;
import com.codepulse.visitlogService.service.ClientMstService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClientMstController {
    private final ClientMstService clientMstService;
    private Logger logger = LoggerFactory.getLogger(ClientMstController.class);

    //http://localhost:8080/clients/v1/all
    @GetMapping("/v1/all")
    public Response getAllClient() {

        Response response = new Response();
        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();

        try {
            logger.info(String.format("get - client/all"));

            ObjectMapper objectMapper = new ObjectMapper();
            List<TbClientMst> clientMsts = clientMstService.getAllClient();

            //Response data encrypted with AES256
            com.codepulse.visitlogService.Common.AES256Chiper aes256Chiper = com.codepulse.visitlogService.Common.AES256Chiper.getInstance();
            String resData = aes256Chiper.encrypt(aes256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(clientMsts));

            response.setData(resData);
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("OK");
        } catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }

    //http://localhost:8080/clients/v1/view?cltCd=101001
    @GetMapping("/v1/view")
    public Response getClient(@RequestParam("cltCd") String cltCd) {

        Response response = new Response();
        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();

        try {
            logger.info(String.format("get - client/%s", cltCd));

            ObjectMapper objectMapper = new ObjectMapper();
            TbClientMst clientMst = clientMstService.getClientById(cltCd);

            //Response data encrypted with AES256
            AES256Chiper aes256Chiper = AES256Chiper.getInstance();
            String resData = aes256Chiper.encrypt(aes256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(clientMst));

            response.setData(resData);
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("OK");
        } catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }

    //http://localhost:8080/clients/v1/enroll?req=JSpWtfSi4gXazZCtmHy4bUxsdHSwucmbyD1HocQ...T0k=&ymd=20211130&hh24=10495122
    @PutMapping("/v1/enroll")
    public Response addClient(@RequestParam("req") String req,
                              @RequestParam("ymd") String ymd,
                              @RequestParam("hh24") String hh24) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, JsonProcessingException {

        Response response = new Response();

        try {
            logger.info(String.format("put - client/v1/enroll? [ymd:%s, hh24:%s, req:%s]", ymd, hh24, req));

            AES256Chiper aes256Chiper = AES256Chiper.getInstance();

            String reqData = aes256Chiper.decrypt(aes256Chiper.getKey(ymd, hh24), req);
            ObjectMapper objectMapper = new ObjectMapper();
            TbClientMst clientMst = objectMapper.readValue(reqData, TbClientMst.class);
            clientMst = clientMstService.addClient(clientMst);

            logger.info(String.format("put - client/%s [reqData:%s]", clientMst.getCltCd(), reqData));

            response.setData("OK");
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("고객사 등록을 완료 하였습니다.");
        } catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }

    //http://localhost:8080/clients/v1/modify?req=JSpWtfSi4gXazZCtmHy4bUxsdHSwucmbyD1HocQ...T0k=&ymd=20211130&hh24=10495122
    @PostMapping("/v1/modify")
    public Response chgShop(@RequestParam("req") String req,
                            @RequestParam("ymd") String ymd,
                            @RequestParam("hh24") String hh24) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, JsonProcessingException {

        Response response = new Response();

        try {
            logger.info(String.format("post - clients/v1/modify? [ymd:%s, hh24:%s, req:%s]", ymd, hh24, req));

            AES256Chiper aes256Chiper = AES256Chiper.getInstance();

            String reqData = aes256Chiper.decrypt(aes256Chiper.getKey(ymd, hh24), req);
            ObjectMapper objectMapper = new ObjectMapper();
            TbClientMst newClientMst = objectMapper.readValue(reqData, TbClientMst.class);

            logger.info(String.format("post - clients/v1/modify? [cltCd=%s, reqData:%s]", newClientMst.getCltCd(), reqData));

            TbClientMst clientMst = clientMstService.getClientById(newClientMst.getCltCd());
            if (clientMst == null) {
                throw new Exception("Client information does not exist.");
            }
            clientMst.setCltNm(newClientMst.getCltNm());
            clientMst.setCeo(newClientMst.getCeo());
            clientMst.setTel(newClientMst.getTel());
            clientMst.setEmail(newClientMst.getEmail());
            clientMst.setBizCd(newClientMst.getBizCd());
            clientMst.setUseYn(newClientMst.getUseYn());
            clientMst = clientMstService.addClient(newClientMst);

            response.setData("OK");
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("고객사 정보를 수정 하였습니다.");
        } catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }

    //http://localhost:8080/clients/v1/delete?cltCd=101001
    @DeleteMapping("/v1/delete")
    public Response delClient(@RequestParam("cltCd") String cltCd) {

        Response response = new Response();
        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();

        try {
            logger.info(String.format("delete - clients/v1/delete? [cltCd:%s]", cltCd));

            clientMstService.removeClientById(cltCd);

            response.setData("OK");
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("고객사 정보를 삭제 하였습니다.");
        } catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }
}
