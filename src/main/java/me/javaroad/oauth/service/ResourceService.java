package me.javaroad.oauth.service;

import java.util.List;
import java.util.Set;
import me.javaroad.oauth.dto.request.ResourceRequest;
import me.javaroad.oauth.dto.response.ResourceResponse;
import me.javaroad.oauth.entity.Resource;
import me.javaroad.oauth.mapper.ResourceMapper;
import me.javaroad.oauth.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author heyx
 */
@Service
@Transactional(readOnly = true)
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
    }

    public Resource getEntity(Long resourceId) {
        return resourceRepository.findOne(resourceId);
    }

    public ResourceResponse getResponse(Long resourceId) {
        Resource resource = resourceRepository.findOne(resourceId);
        return resourceMapper.mapEntityToResponse(resource);
    }

    @Transactional
    public ResourceResponse create(ResourceRequest resourceRequest) {
        Resource resource = resourceMapper.mapRequestToEntity(resourceRequest);
        resource = resourceRepository.save(resource);
        return resourceMapper.mapEntityToResponse(resource);
    }

    @Transactional
    public ResourceResponse modify(ResourceRequest resourceRequest) {
        Resource resource = resourceMapper.mapRequestToEntity(resourceRequest);
        resource = resourceRepository.save(resource);
        return resourceMapper.mapEntityToResponse(resource);
    }

    @Transactional
    public void delete(Long resourceId) {
        resourceRepository.delete(resourceId);
    }

    Set<Resource> getResourceByIds(Set<Long> resourceIds) {
        return resourceRepository.findByIdIn(resourceIds);
    }

    public Page<ResourceResponse> getPage(Pageable pageable) {
        Page<Resource> resourcePage = resourceRepository.findAll(pageable);
        return resourcePage.map(resourceMapper::mapEntityToResponse);
    }

    public List<ResourceResponse> getAll() {
        return resourceMapper.mapEntityToResponse(resourceRepository.findAll());
    }
}
