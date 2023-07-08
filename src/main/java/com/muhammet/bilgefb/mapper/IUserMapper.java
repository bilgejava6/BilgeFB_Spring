package com.muhammet.bilgefb.mapper;

import com.muhammet.bilgefb.dto.request.RegisterRequestDto;
import com.muhammet.bilgefb.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User userFromDto(final RegisterRequestDto dto);
}
