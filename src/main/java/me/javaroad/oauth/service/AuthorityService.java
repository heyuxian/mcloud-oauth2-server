package me.javaroad.oauth.service;

import java.util.Objects;
import java.util.Set;
import me.javaroad.common.exception.DataNotFoundException;
import me.javaroad.oauth.dto.request.AuthorityRequest;
import me.javaroad.oauth.dto.response.AuthorityResponse;
import me.javaroad.oauth.entity.Authority;
import me.javaroad.oauth.mapper.AuthorityMapper;
import me.javaroad.oauth.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author heyx
 */
@Service
@Transactional(readOnly = true)
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper mapper;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper mapper) {
        this.authorityRepository = authorityRepository;
        this.mapper = mapper;
    }

    Authority getEntity(Long authorityId) {
        return authorityRepository.findOne(authorityId);
    }

    @Transactional
    public AuthorityResponse create(AuthorityRequest authorityRequest) {
        Authority authority = mapper.mapRequestToEntity(authorityRequest);
        authority = authorityRepository.save(authority);
        return mapper.mapEntityToResponse(authority);
    }

    @Transactional
    public AuthorityResponse modify(Long authorityId, AuthorityRequest authorityRequest) {
        Authority authority = getEntity(authorityId);
        if(Objects.isNull(authority)){
            throw new DataNotFoundException("Authority[id=%s] not found", authorityId);
        }
        authority.setName(authorityRequest.getName());
        authority = authorityRepository.save(authority);
        return mapper.mapEntityToResponse(authority);
    }

    @Transactional
    public void delete(Long authorityId) {
        authorityRepository.delete(authorityId);
    }

    Set<Authority> getAuthorityByIds(Set<Long> authorityIds) {
        return authorityRepository.findByIdIn(authorityIds);
    }
}
