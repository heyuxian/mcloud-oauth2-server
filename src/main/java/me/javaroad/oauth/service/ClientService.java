package me.javaroad.oauth.service;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import me.javaroad.common.exception.DataConflictException;
import me.javaroad.common.exception.DataNotFoundException;
import me.javaroad.common.exception.InvalidParameterException;
import me.javaroad.oauth.dto.request.CreateClientRequest;
import me.javaroad.oauth.dto.request.ModifyClientRequest;
import me.javaroad.oauth.dto.response.ClientResponse;
import me.javaroad.oauth.entity.*;
import me.javaroad.oauth.mapper.ClientMapper;
import me.javaroad.oauth.repository.ClientRepository;
import me.javaroad.oauth.util.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

/**
 * @author heyx
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    private final ResourceService resourceService;
    private final ApprovalService approvalService;
    private final AuthorityService authorityService;
    private final ScopeService scopeService;
    private final UserService userService;

    public Client getEntity(Long clientId) {
        return clientRepository.findOne(clientId);
    }

    @Transactional
    public ClientResponse create(String username, CreateClientRequest clientRequest) {
        Client client = clientRepository.findByUserUsername(username);
        if (Objects.nonNull(client)) {
            throw new DataConflictException("User client[username=%s] already exists", username);
        }
        client = clientMapper.mapRequestToEntity(clientRequest);
        client.setClientId(IdUtils.uuid());
        client.setStatus(Status.PENDING);
        User user = userService.getNotNullEntity(username);
        client.setUser(user);
        client.setClientSecret(passwordEncoder.encode(clientRequest.getClientSecret()));
        // todo
        //setResourceToClient(client, clientRequest.getResourceIds());
        //setAutoApproveToClient(client, clientRequest.getAutoApproveIds());
        setScopeToClient(client, clientRequest.getScopeIds());
        client = clientRepository.save(client);
        return clientMapper.mapEntityToResponse(client);
    }

    @Transactional
    public ClientResponse modify(String username, ModifyClientRequest clientRequest) {
        Client client = clientRepository.findByUserUsername(username);
        if (Objects.isNull(client)) {
            throw new DataNotFoundException("User client[username=%s] not found", username);
        }

        client.setRedirectUri(clientRequest.getRedirectUri());
        if (StringUtils.isNotBlank(clientRequest.getClientSecret())) {
            client.setClientSecret(passwordEncoder.encode(clientRequest.getClientSecret()));
        }
        return clientMapper.mapEntityToResponse(clientRepository.save(client));
    }

    private void setScopeToClient(Client client, Set<Long> scopeIds) {
        Set<Scope> scopes = scopeService.getScopeByIds(scopeIds);
        if (CollectionUtils.isEmpty(scopes)) {
            throw new InvalidParameterException("invalid scopeIds");
        }
        client.setScope(scopes);
    }

    //todo
    private void setAuthorityToClient(Client client, Set<Long> authorityIds) {
        Set<Authority> authorities = authorityService.getAuthorityByIds(authorityIds);
        if (CollectionUtils.isEmpty(authorities)) {
            throw new InvalidParameterException("invalid authorityIds");
        }
        client.setAuthorities(authorities);
    }

    private void setAutoApproveToClient(Client client, Set<Long> autoApproveIds) {
        if (CollectionUtils.isEmpty(autoApproveIds)) {
            return;
        }
        Set<Approval> approvals = approvalService.getApprovalByIds(autoApproveIds);
        if (CollectionUtils.isEmpty(approvals)) {
            throw new InvalidParameterException("invalid autoApproveIds");
        }
        client.setAutoApprove(approvals);
    }

    private void setResourceToClient(Client client, Set<Long> clientIds) {
        Set<Resource> resources = resourceService.getResourceByIds(clientIds);
        if (CollectionUtils.isEmpty(resources)) {
            throw new InvalidParameterException("invalid resourceIds");
        }
        client.setResources(resources);
    }

    @Transactional
    public void delete(Long clientId) {
        Client client = getEntity(clientId);
        if (Objects.isNull(client)) {
            throw new DataNotFoundException("client[id=%s] not found", clientId);
        }
        clientRepository.delete(client);
    }

    public ClientResponse getResponseByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId);
        return clientMapper.mapEntityToResponse(client);
    }

    public Page<ClientResponse> getPage(Pageable pageable) {
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.map(clientMapper::mapEntityToResponse);
    }

    private Set<GrantType> defaultGrantTypes() {
        return Sets.newHashSet(GrantType.CODE);
    }

    public ClientResponse getResponseByUsername(String username) {
        return clientMapper.mapEntityToResponse(clientRepository.findByUserUsername(username));
    }
}
