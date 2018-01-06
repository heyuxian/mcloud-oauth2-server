package me.javaroad.mcloud.oauth.mapper;

import java.util.List;
import me.javaroad.mcloud.oauth.dto.response.ScopeResponse;
import me.javaroad.mcloud.oauth.dto.request.ScopeRequest;
import me.javaroad.mcloud.oauth.entity.Scope;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author heyx
 */
@Mapper(componentModel = "spring")
public interface ScopeMapper {

    ScopeMapper INSTANCE = Mappers.getMapper(ScopeMapper.class);

    Scope mapRequestToEntity(ScopeRequest scopeRequest);

    ScopeResponse mapEntityToResponse(Scope scope);

    List<ScopeResponse> mapEntityToResponse(List<Scope> scopes);
}
