package com.codepulse.visitlogService.restful.controller;

import com.codepulse.visitlogService.common.AES256Chiper;
import com.codepulse.visitlogService.common.CommLib;
import com.codepulse.visitlogService.common.base.CommonResponse;
import com.codepulse.visitlogService.common.base.ErrorMessage;
import com.codepulse.visitlogService.restful.dto.ClientDto;
import com.codepulse.visitlogService.restful.dto.base.CommonRequest;
import com.codepulse.visitlogService.restful.dto.base.CommonResult;
import com.codepulse.visitlogService.restful.dto.req.ClientReqDto;
import com.codepulse.visitlogService.restful.dto.res.ClientResDto;
import com.codepulse.visitlogService.restful.service.ClientService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.imageio.plugins.png.PNGImageReader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.spi.NameTokenizer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/clients")
@Tag(name = "고객사(CLIENTS)", description = "고객사 과 사용자(유저, 유저 그룹, 롤) 관련 API")
public class ClientController {
    private final ClientService clientService;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    /**
     * 고객사 전체조회
     */
    @Operation(summary = "전체 고객사 조회", description = "전체 고객사 조회")
    @GetMapping
    public CommonResult fetchAllClient() {

        log.info("## getAllClient()");

        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();
        CommonResult commonResult;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ClientDto> clients = clientService.getAllClient();

            commonResult = CommonResult.builder()
                    .success(true)
                    .code(0)
                    .data(AES256Chiper.encrypt(AES256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(clients))) //CommonResponse data encrypted with AES256
                    .time(String.format("%s%s", ymd, hh24))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            commonResult = CommonResult.builder()
                    .success(false)
                    .code(ErrorMessage.NOT_READY_SERVER.getCode())
                    .msg(ErrorMessage.NOT_READY_SERVER.getDescription())
                    .build();
        }

        return commonResult;
    }

    /**
     * 지정 고객사 조회
     * @param id 고객사 코드
     */
    @Operation(summary = "지정 고객사 조회", description = "지정 고객사 조회")
    @GetMapping("/{id}")
    public CommonResult fetchClientById(@PathVariable String id) {
        log.info("## getClientById(id:{})", id);

        String ymd = CommLib.getCurrentDate();
        String hh24 = CommLib.getCurrentTime();
        CommonResult commonResult;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClientDto client = clientService.getClientById(id);

            commonResult = CommonResult.builder()
                    .success(true)
                    .code(0)
                    .data(AES256Chiper.encrypt(AES256Chiper.getKey(ymd, hh24), objectMapper.writeValueAsString(client))) //CommonResponse data encrypted with AES256
                    .time(String.format("%s%s", ymd, hh24))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            commonResult = CommonResult.builder()
                    .success(false)
                    .code(ErrorMessage.NOT_READY_SERVER.getCode())
                    .msg(ErrorMessage.NOT_READY_SERVER.getDescription())
                    .build();
        }
        return commonResult;
    }

    /**
     * 고객사 신규 등록
     */
    @PostMapping
    public CommonResult enrollClient(@RequestBody CommonRequest request) {
        log.info("## enrollClient(req:{}, time:{})", request.getReq(), request.getTime());

        CommonResult commonResult;

        try {
            String reqData = AES256Chiper.decrypt(AES256Chiper.getKey(request.getTime().substring(0, 8), request.getTime().substring(8, request.getTime().length())), request.getReq());

            ClientResDto clientResDto = clientService.regClient(objectMapper.readValue(reqData, ClientReqDto.class));

            commonResult = CommonResult.builder()
                    .success(true)
                    .code(0)
                    .data(AES256Chiper.encrypt(AES256Chiper.getKey(request.getTime().substring(0, 8), request.getTime().substring(8, request.getTime().length())), objectMapper.writeValueAsString(clientResDto))) //CommonResponse data encrypted with AES256
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
     * 고객사 정보 수정
     */
    @PutMapping
    public CommonResult modifyClient(@RequestBody CommonRequest request) {
        log.info("## modifyClient(req:{}, time:{})", request.getReq(), request.getTime());

        CommonResult commonResult;

        try {
            String reqData = AES256Chiper.decrypt(AES256Chiper.getKey(request.getTime().substring(0, 8), request.getTime().substring(8, request.getTime().length())), request.getReq());

            ClientResDto clientResDto = clientService.chgClient(objectMapper.readValue(reqData, ClientReqDto.class));



            commonResult = CommonResult.builder()
                    .success(true)
                    .code(0)
                    .data(AES256Chiper.encrypt(AES256Chiper.getKey(request.getTime().substring(0, 8), request.getTime().substring(8, 8)), objectMapper.writeValueAsString(clientResDto))) //CommonResponse data encrypted with AES256
                    .time(String.format("%s%s", request.getTime().substring(0, 8), request.getTime().substring(8, request.getTime().length())))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            commonResult = CommonResult.builder()
                    .success(false)
                    .code(ErrorMessage.NOT_READY_SERVER.getCode())
                    .msg(ErrorMessage.NOT_READY_SERVER.getDescription())
                    .build();
        }

        return commonResult;
    }
}
