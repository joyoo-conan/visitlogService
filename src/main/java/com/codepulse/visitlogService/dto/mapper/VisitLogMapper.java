package com.codepulse.visitlogService.dto.mapper;

import com.codepulse.visitlogService.dto.VisitLogDto;
import com.codepulse.visitlogService.model.TbVisitLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VisitLogMapper {
    VisitLogMapper INSTANCE = Mappers.getMapper(VisitLogMapper.class);

    VisitLogDto of(TbVisitLog visitLog);
}
