package com.codepulse.visitlogService.restful.service;

import com.codepulse.visitlogService.restful.dto.ClientDto;
import com.codepulse.visitlogService.restful.dto.VisitLogDto;
import com.codepulse.visitlogService.restful.dto.req.VisitLogReqDto;
import com.codepulse.visitlogService.restful.dto.res.ClientResDto;
import com.codepulse.visitlogService.restful.dto.res.VisitLogResDto;
import com.codepulse.visitlogService.restful.model.Client;
import com.codepulse.visitlogService.restful.model.VisitLog;
import com.codepulse.visitlogService.restful.repository.VisitLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitLogService {

    private final VisitLogRepository visitLogRepository;
    private final ModelMapper modelMapper;

    /**
     * 방문기록 등록
     */
    public VisitLogResDto regVisitlog(VisitLogReqDto visitlogReq) {
        VisitLog visitLog = modelMapper.map(visitlogReq, VisitLog.class);
        return modelMapper.map(visitLogRepository.save(visitLog), VisitLogResDto.class);
    }

    /**
     * 방문기록 조회
     * @param cltCd - 고객사 코드
     * @param visitDate - 방문 날짜(조회 날짜)
     * @return
     */
    public List<VisitLogResDto> getVisitlogByDate(String cltCd, String visitDate) {
        List<VisitLog> visitLogs = visitLogRepository.findByCltCdAndVisitDate(cltCd, visitDate);

        return visitLogs
                .stream()
                .map(x -> modelMapper.map(x, VisitLogResDto.class))
                .collect(Collectors.toList());
    }
}
