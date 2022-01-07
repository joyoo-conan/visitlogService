package com.codepulse.visitlogService.Controller;

import com.codepulse.visitlogService.Common.AES256Chiper;
import com.codepulse.visitlogService.Common.CommLib;
import com.codepulse.visitlogService.Entity.Response;
import com.codepulse.visitlogService.Entity.VisitLog;
import com.codepulse.visitlogService.Service.VisitLogService;
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
@RequestMapping(value = "/visitlog", produces = {MediaType.APPLICATION_JSON_VALUE})
public class VisitLogController {
    private final VisitLogService visitLogService;
    private Logger logger = LoggerFactory.getLogger(VisitLogController.class);

    //http://localhost:8080/visitlog/v1/record?req=JSpWtfS1wK5yVlYvx+w479SK78uYTII7wW+/E0HiWdI98+UkxdrsXfij3IS9cUsHoTRcFDJQal3aZ6aU07J75K9Dl6fbF4f2v+KeCY2r92j3J94jRLh0FWo68evoXkTLm7FVX2t19WCmGM5tH9BFqwdokKFWmm7/UNBSMtg70j2bW4WzQs8S5OfdNtA4y8DwmbYlmaZIi4gXazZCtmHy4bUxsdHSwucmbyD1HocQT0k=&ymd=20211130&hh24=10495122
    @PutMapping("/v1/record")
    public Response regVisitLog(@RequestParam("req") String req,
                                @RequestParam("ymd") String ymd,
                                @RequestParam("hh24") String hh24) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, JsonProcessingException {
        Response response = new Response();

        try {
            logger.info(String.format("put - visitlog/v1/record? [ymd:%s, hh24:%s, req:%s]", ymd, hh24, req));

            AES256Chiper aes256Chiper = AES256Chiper.getInstance();

            String reqData = aes256Chiper.decrypt(aes256Chiper.getKey(ymd, hh24), req);

            System.out.println("reqData:" + reqData);

            ObjectMapper objectMapper = new ObjectMapper();
            VisitLog visitLog = objectMapper.readValue(reqData, VisitLog.class);
            visitLog.setVisitDate(ymd);
            visitLog.setVisitTime(hh24);
            visitLog = visitLogService.recordVisitLog(visitLog);

            logger.info(String.format("put - visitlog/v1/record? %s [reqData:%s]", visitLog.getCltCd(), reqData));

            response.setData("OK");
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("방문 등록을 완료 하였습니다.");
        } catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }

    //http://localhost:8080/visitlog/v1/view?cltCd=101001&visitDate=20211126
    @GetMapping("/v1/view")
    public Response getVisitLog(@RequestParam("cltCd") String cltCd,
                                @RequestParam("visitDate") String visitDate) {

        Response response = new Response();
        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();

        try {
            logger.info(String.format("get - visitlog/v1/view?cltCd=%s&visitDate=%s", cltCd, visitDate));

            ObjectMapper objectMapper = new ObjectMapper();
            List<VisitLog> visitLogs = visitLogService.getByCltCdAndVisitDate(cltCd, visitDate);

            AES256Chiper aes256Chiper = AES256Chiper.getInstance();

            String resData = aes256Chiper.encrypt(aes256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(visitLogs));

            response.setData(resData);
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("OK");
        }
        catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }

    //http://localhost:8080/visitlog/v2/view?cltCd=101002&start=20211109&end=20211209
    @GetMapping("/v2/view")
    public Response getVisitLogBetween(@RequestParam("cltCd") String cltCd,
                                       @RequestParam("start") String start,
                                       @RequestParam("end") String end) {
        Response response = new Response();
        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();

        try {
            logger.info(String.format("get - visitlog/v2/view?cltCd=%s&start=%s&end=%s", cltCd, start, end));

            ObjectMapper objectMapper = new ObjectMapper();
            List<VisitLog> visitLogs = visitLogService.getByCltCdAndVisitDateBetween(cltCd, start, end);

            AES256Chiper aes256Chiper = AES256Chiper.getInstance();
            String resData = aes256Chiper.encrypt(aes256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(visitLogs));

            response.setData(resData);
            response.setTime(ymd + hh24);
            response.setVal(0);
            response.setMsg("OK");
        }catch (Exception ex) {
            logger.error(String.format("Exception:%s", ex.getMessage()));

            response.setData("ERROR");
            response.setTime(ymd + hh24);
            response.setVal(-1);
            response.setMsg(ex.getMessage());
        }

        return response;
    }
}
