package me.javaroad.mcloud.oauth.core;

import java.util.Objects;
import me.javaroad.mcloud.oauth.dto.response.ClientResponse;
import me.javaroad.mcloud.oauth.service.ClientService;
import me.javaroad.mcloud.oauth.core.mapper.ClientDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * @author heyx
 */
@Component
public class CustomClientDetailsService implements ClientDetailsService {

    private final ClientService clientService;
    private final ClientDetailMapper clientDetailMapper;

    @Autowired
    public CustomClientDetailsService(ClientService clientService, ClientDetailMapper clientDetailMapper) {
        this.clientService = clientService;
        this.clientDetailMapper = clientDetailMapper;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientResponse client = clientService.getResponseByClientId(clientId);
        if (Objects.isNull(client)) {
            throw new ClientRegistrationException("client[clientId=" + clientId + "] not found");
        }
        return clientDetailMapper.mapClientToDetails(client);
    }
}
