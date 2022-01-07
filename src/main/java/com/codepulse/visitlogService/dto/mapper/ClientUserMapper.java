package com.codepulse.visitlogService.dto.mapper;

import com.codepulse.visitlogService.dto.ClientUserDto;
import com.codepulse.visitlogService.model.TbClientUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientUserMapper {
    ClientUserMapper INSTANCE = Mappers.getMapper(ClientUserMapper.class);

    ClientUserDto of(TbClientUser clientUser);
}
