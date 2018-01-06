package me.javaroad.mcloud.oauth.controller.consoleapi;

import com.google.common.collect.Sets;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.dto.response.ClientResponse;
import me.javaroad.mcloud.oauth.dto.request.CreateClientRequest;
import me.javaroad.mcloud.oauth.dto.request.ModifyClientRequest;
import me.javaroad.mcloud.oauth.entity.GrantType;
import me.javaroad.mcloud.oauth.service.ClientService;
import me.javaroad.mcloud.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(OAuthConstants.CONSOLE_API_PREFIX + "/clients")
public class ClientApi {

    private final ClientService clientService;

    @Autowired
    public ClientApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Create Client", httpMethod = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse createClient(@RequestBody @Valid CreateClientRequest clientRequest,
        @CurrentUser String username) {
        clientRequest.setGrantTypes(Sets.newHashSet(GrantType.CODE));
        return clientService.create(username, clientRequest);
    }

    @ApiOperation(value = "Modify Client", httpMethod = "PATCH")
    @PatchMapping
    public ClientResponse patchClient(@RequestBody @Valid ModifyClientRequest clientRequest,
        @CurrentUser String username) {
        return clientService.modify(username, clientRequest);
    }

}
