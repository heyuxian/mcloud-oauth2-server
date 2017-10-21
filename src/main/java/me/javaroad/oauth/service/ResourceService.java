package me.javaroad.oauth.service;

import java.util.Set;
import me.javaroad.oauth.dto.request.ResourceRequest;
import me.javaroad.oauth.dto.response.ResourceResponse;
import me.javaroad.oauth.entity.Resource;
import me.javaroad.oauth.mapper.ResourceMapper;
import me.javaroad.oauth.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author heyx
 */
@Service
@Transactional(readOnly = true)
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper mapper;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, ResourceMapper mapper) {
        this.resourceRepository = resourceRepository;
        this.mapper = mapper;
    }

    public ResourceResponse getResource(Long resourceId) {
        Resource resource = resourceRepository.findOne(resourceId);
        return mapper.mapEntityToResponse(resource);
    }

    @Transactional
    public ResourceResponse createResource(ResourceRequest resourceRequest) {
        Resource resource = mapper.mapRequestToEntity(resourceRequest);
        resource = resourceRepository.save(resource);
        return mapper.mapEntityToResponse(resource);
    }

    @Transactional
    public ResourceResponse modifyResource(ResourceRequest resourceRequest) {
        Resource resource = mapper.mapRequestToEntity(resourceRequest);
        resource = resourceRepository.save(resource);
        return mapper.mapEntityToResponse(resource);
    }

    @Transactional
    public void deleteResource(Long resourceId) {
        resourceRepository.delete(resourceId);
    }

    Set<Resource> getResourceByIds(Set<Long> resourceIds) {
        return resourceRepository.findByIdIn(resourceIds);
    }
}
