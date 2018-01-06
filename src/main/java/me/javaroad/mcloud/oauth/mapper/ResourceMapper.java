package me.javaroad.mcloud.oauth.mapper;

import java.util.List;
import java.util.Set;
import me.javaroad.mcloud.oauth.dto.request.ResourceRequest;
import me.javaroad.mcloud.oauth.entity.Resource;
import me.javaroad.mcloud.oauth.dto.response.ResourceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author heyx
 */
@Mapper(componentModel = "spring")
public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    Resource mapRequestToEntity(ResourceRequest resourceRequest);

    Set<ResourceResponse> mapEntityToResponse(Set<Resource> resource);

    ResourceResponse mapEntityToResponse(Resource resource);

    List<ResourceResponse> mapEntityToResponse(List<Resource> resources);
}
