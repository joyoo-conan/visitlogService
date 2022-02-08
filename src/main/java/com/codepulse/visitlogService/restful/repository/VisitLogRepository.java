package com.codepulse.visitlogService.restful.repository;

import com.codepulse.visitlogService.restful.model.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitLogRepository extends JpaRepository<VisitLog, Long> {

    public List<VisitLog> findByCltCdAndVisitDate(String cltCd, String visitDate);
}
