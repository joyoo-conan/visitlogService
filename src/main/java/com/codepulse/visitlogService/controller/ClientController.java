package com.codepulse.visitlogService.Controller;

import com.codepulse.visitlogService.Common.AES256Chiper;
import com.codepulse.visitlogService.Common.CommLib;
import com.codepulse.visitlogService.Entity.Client;
import com.codepulse.visitlogService.Entity.Response;
import com.codepulse.visitlogService.Service.ClientService;
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
@RequestMapping(value = "/clients", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClientController {
    private final ClientService clientService;
    private Logger logger = LoggerFactory.getLogger(ClientController.class);

    //http://localhost:8080/clients/v1/all
    @GetMapping("/v1/all")
    public Response getAllClient() {

        Response response = new Response();
        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();

        try {
            logger.info(String.format("get - client/all"));

            ObjectMapper objectMapper = new ObjectMapper();
            List<Client> clients = clientService.getAllClient();

            //Response data encrypted with AES256
            AES256Chiper aes256Chiper = AES256Chiper.getInstance();
            String resData = aes256Chiper.encrypt(aes256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(clients));

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
            Client client = clientService.getClientById(cltCd);

            //Response data encrypted with AES256
            AES256Chiper aes256Chiper = AES256Chiper.getInstance();
            String resData = aes256Chiper.encrypt(aes256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(client));

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
            Client client = objectMapper.readValue(reqData, Client.class);
            client = clientService.addClient(client);

            logger.info(String.format("put - client/%s [reqData:%s]", client.getCltCd(), reqData));

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
            Client newClient = objectMapper.readValue(reqData, Client.class);

            logger.info(String.format("post - clients/v1/modify? [cltCd=%s, reqData:%s]", newClient.getCltCd(), reqData));

            Client client = clientService.getClientById(newClient.getCltCd());
            if (client == null) {
                throw new Exception("Client information does not exist.");
            }
            client.setCltNm(newClient.getCltNm());
            client.setCeo(newClient.getCeo());
            client.setTel(newClient.getTel());
            client.setEmail(newClient.getEmail());
            client.setBizCd(newClient.getBizCd());
            client.setUseYn(newClient.getUseYn());
            client = clientService.addClient(client);

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

            clientService.removeClientById(cltCd);

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
