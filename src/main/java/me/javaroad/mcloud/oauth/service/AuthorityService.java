package me.javaroad.mcloud.oauth.service;

import com.google.common.collect.Sets;
import java.util.Objects;
import java.util.Set;
import me.javaroad.common.exception.DataNotFoundException;
import me.javaroad.mcloud.oauth.controller.OAuthConstants.Default;
import me.javaroad.mcloud.oauth.dto.request.AuthorityRequest;
import me.javaroad.mcloud.oauth.dto.response.AuthorityResponse;
import me.javaroad.mcloud.oauth.entity.Authority;
import me.javaroad.mcloud.oauth.entity.User.UserType;
import me.javaroad.mcloud.oauth.mapper.AuthorityMapper;
import me.javaroad.mcloud.oauth.repository.AuthorityRepository;
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
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    Authority getEntity(Long authorityId) {
        return authorityRepository.findOne(authorityId);
    }

    Authority getEntity(String name) {
        return authorityRepository.findByName(name);
    }

    @Transactional
    public AuthorityResponse create(AuthorityRequest authorityRequest) {
        Authority authority = authorityMapper.mapRequestToEntity(authorityRequest);
        authority = authorityRepository.save(authority);
        return authorityMapper.mapEntityToResponse(authority);
    }

    @Transactional
    public AuthorityResponse modify(Long authorityId, AuthorityRequest authorityRequest) {
        Authority authority = getEntity(authorityId);
        if (Objects.isNull(authority)) {
            throw new DataNotFoundException("Authority[id=%s] not found", authorityId);
        }
        authority.setName(authorityRequest.getName());
        authority = authorityRepository.save(authority);
        return authorityMapper.mapEntityToResponse(authority);
    }

    @Transactional
    public void delete(Long authorityId) {
        authorityRepository.delete(authorityId);
    }

    Set<Authority> getAuthorityByIds(Set<Long> authorityIds) {
        return authorityRepository.findByIdIn(authorityIds);
    }

    public Page<AuthorityResponse> getPage(Pageable pageable) {
        Page<Authority> authorityPage = authorityRepository.findAll(pageable);
        return authorityPage.map(authorityMapper::mapEntityToResponse);
    }

    Set<Authority> getDefaultAuthorities(UserType userType) {
        Authority authority;
        if (UserType.USER.equals(userType)) {
            authority = getEntity(Default.DEFAULT_USER_AUTHORITY);
        } else {
            authority = getEntity(Default.DEFAULT_DEVELOPER_AUTHORITY);
        }
        if (Objects.isNull(authority)) {
            throw new DataNotFoundException("Default authority[userType=%s] not exists", userType);
        }
        return Sets.newHashSet(authority);
    }
}
