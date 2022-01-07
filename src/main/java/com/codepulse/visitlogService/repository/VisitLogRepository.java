package com.codepulse.visitlogService.Repository;

import com.codepulse.visitlogService.model.TbVisitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitLogRepository extends JpaRepository<TbVisitLog, Long> {
    //The visit log record
    TbVisitLog save(TbVisitLog visitLog);

    //
    //List<VisitLog> findByCltCd(String cltCd);

    //
    List<TbVisitLog> findByCltCdAndVisitDate(String cltCd, String visitDate);

    //
    List<TbVisitLog> findByCltCdAndVisitDateBetween(String cltCd, String start, String end);
}
