package com.codepulse.visitlogService.Service;

import com.codepulse.visitlogService.Repository.VisitLogRepository;
import com.codepulse.visitlogService.model.TbVisitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitLogService {
    private final VisitLogRepository visitLogRepository;

    @Autowired
    public VisitLogService(VisitLogRepository visitLogRepository) { this.visitLogRepository = visitLogRepository; }

    //Record visit log
    public TbVisitLog recordVisitLog(TbVisitLog visitLog) { return visitLogRepository.save(visitLog); }

    //Query visit history by cltCd
    //public List<TbVisitLog> getVisitLogByCltCd(String cltCd) { return visitLogRepository.findByCltCd(cltCd); }

    //Query visit history by cltCd, visitDate
    public List<TbVisitLog> getByCltCdAndVisitDate(String cltCd, String visitDate) { return visitLogRepository.findByCltCdAndVisitDate(cltCd, visitDate); }

    //Query visit history by cltCd, visitDate period
    public List<TbVisitLog> getByCltCdAndVisitDateBetween(String cltCd, String startDt, String endDt) { return visitLogRepository.findByCltCdAndVisitDateBetween(cltCd, startDt, endDt); }
}
