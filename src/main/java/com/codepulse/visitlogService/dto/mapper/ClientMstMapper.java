package com.codepulse.visitlogService.dto.mapper;

import com.codepulse.visitlogService.dto.ClientMstDto;
import com.codepulse.visitlogService.model.TbClientMst;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientMstMapper {
    ClientMstMapper INSTANCE = Mappers.getMapper(ClientMstMapper.class);

    ClientMstDto of(TbClientMst clientMst);
}
