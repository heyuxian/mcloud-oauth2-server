package me.javaroad.mcloud.oauth.mapper;

import java.util.List;
import me.javaroad.mcloud.oauth.dto.request.DeveloperInfoRequest;
import me.javaroad.mcloud.oauth.dto.response.DeveloperInfoResponse;
import me.javaroad.mcloud.oauth.entity.DeveloperInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeveloperInfoMapper {

    DeveloperInfoMapper INSTANCE = Mappers.getMapper(DeveloperInfoMapper.class);

    DeveloperInfo mapRequestToEntity(DeveloperInfoRequest developerInfoRequest);

    DeveloperInfoResponse mapEntityToResponse(DeveloperInfo developerInfo);

    List<DeveloperInfoResponse> mapEntityToResponse(List<DeveloperInfo> developerInfos);

    void updateEntityFromRequest(DeveloperInfoRequest developerInfoRequest, @MappingTarget DeveloperInfo developerInfo);
}
