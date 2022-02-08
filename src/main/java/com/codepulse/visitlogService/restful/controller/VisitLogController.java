package com.codepulse.visitlogService.restful.controller;

import com.codepulse.visitlogService.common.AES256Chiper;
import com.codepulse.visitlogService.common.CommLib;
import com.codepulse.visitlogService.restful.dto.base.CommonRequest;
import com.codepulse.visitlogService.restful.dto.base.CommonResult;
import com.codepulse.visitlogService.restful.dto.req.VisitLogReqDto;
import com.codepulse.visitlogService.restful.dto.res.VisitLogResDto;
import com.codepulse.visitlogService.restful.repository.VisitLogRepository;
import com.codepulse.visitlogService.restful.service.VisitLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/v1/visitlog")
@Tag(name = "방명록(VISITLOG)", description = "고객사 방문기록 관련 API")
public class VisitLogController {

    private final VisitLogService visitLogService;
    private final ObjectMapper objectMapper;

    /**
     * 고객사 방문기록 등록
     */
    @PostMapping
    public CommonResult enrollVisitlog(@RequestBody CommonRequest request) {
        log.info("## enrollVisitlog(req:{}, time:{})", request.getReq(), request.getTime());

        CommonResult commonResult;

        try {
            String reqData = AES256Chiper.decrypt(AES256Chiper.getKey(request.getTime().substring(0, 8), request.getTime().substring(8, request.getTime().length())), request.getReq());

            VisitLogResDto visitLogResDto = visitLogService.regVisitlog(objectMapper.readValue(reqData, VisitLogReqDto.class));

            commonResult = CommonResult.builder()
                    .success(true)
                    .code(0)
                    .data(AES256Chiper.encrypt(AES256Chiper.getKey(request.getTime().substring(0, 8), request.getTime().substring(8, request.getTime().length())), objectMapper.writeValueAsString(visitLogResDto))) //CommonResponse data encrypted with AES256
                    .time(String.format("%s%s", request.getTime().substring(0, 8), request.getTime().substring(8, request.getTime().length())))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            commonResult = CommonResult.builder()
                    .success(false)
                    .code(e.hashCode())
                    .msg(e.getMessage())
                    .build();
        }

        return commonResult;
    }

    /**
     * 고객사 방무기록 조
     * @param reqDate
     * @return
     */
    @GetMapping("/{cltCd}")
    public CommonResult fetchVisitLogByDate(@PathVariable String cltCd, @RequestParam String reqDate) {
        log.info("## fetchVisitLogByDate(cltCd:{}, reqData:{})", cltCd, reqDate);

        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();
        CommonResult commonResult;

        try {
            List<VisitLogResDto> visitlogs = visitLogService.getVisitlogByDate(cltCd, reqDate);

            commonResult = CommonResult.builder()
                    .success(true)
                    .code(0)
                    .data(AES256Chiper.encrypt(AES256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(visitlogs))) //CommonResponse data encrypted with AES256
                    .time(String.format("%s%s", ymd, hh24))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            commonResult = CommonResult.builder()
                    .success(false)
                    .code(e.hashCode())
                    .msg(e.getMessage())
                    .build();
        }

        return commonResult;
    }
}
