package me.javaroad.mcloud.oauth.mapper;

import me.javaroad.mcloud.oauth.dto.request.AuthorityRequest;
import me.javaroad.mcloud.oauth.dto.response.AuthorityResponse;
import me.javaroad.mcloud.oauth.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author heyx
 */
@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    AuthorityResponse mapEntityToResponse(Authority authority);

    Authority mapRequestToEntity(AuthorityRequest authorityRequest);
}
