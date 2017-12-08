package me.javaroad.oauth.service;

import java.util.List;
import java.util.Set;
import me.javaroad.oauth.dto.request.ScopeRequest;
import me.javaroad.oauth.dto.response.ScopeResponse;
import me.javaroad.oauth.entity.Scope;
import me.javaroad.oauth.mapper.ScopeMapper;
import me.javaroad.oauth.repository.ScopeRepository;
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
public class ScopeService {

    private final ScopeRepository scopeRepository;
    private final ScopeMapper scopeMapper;

    @Autowired
    public ScopeService(ScopeRepository scopeRepository, ScopeMapper scopeMapper) {
        this.scopeRepository = scopeRepository;
        this.scopeMapper = scopeMapper;
    }

    public Scope getScope(Long scopeId) {
        return scopeRepository.findOne(scopeId);
    }

    @Transactional
    public ScopeResponse createScope(ScopeRequest scopeRequest) {
        Scope scope = scopeMapper.mapRequestToEntity(scopeRequest);
        scope = scopeRepository.save(scope);
        return scopeMapper.mapEntityToResponse(scope);
    }

    @Transactional
    public ScopeResponse modifyScope(ScopeRequest scopeRequest) {
        Scope scope = scopeMapper.mapRequestToEntity(scopeRequest);
        scope = scopeRepository.save(scope);
        return scopeMapper.mapEntityToResponse(scope);
    }

    @Transactional
    public void deleteScope(Long scopeId) {
        scopeRepository.delete(scopeId);
    }

    Set<Scope> getScopeByIds(Set<Long> scopeIds) {
        return scopeRepository.findByIdIn(scopeIds);
    }

    public Page<ScopeResponse> getPage(Pageable pageable) {
        Page<Scope> scopePage = scopeRepository.findAll(pageable);
        return scopePage.map(scopeMapper::mapEntityToResponse);
    }

    public List<ScopeResponse> getAll() {
        return scopeMapper.mapEntityToResponse(scopeRepository.findAll());
    }
}
