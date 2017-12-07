package me.javaroad.oauth.controller.consoleapi;

import static me.javaroad.oauth.controller.OAuthConstants.CONSOLE_API_PREFIX;

import com.google.common.collect.Sets;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import me.javaroad.oauth.dto.request.CreateClientRequest;
import me.javaroad.oauth.dto.request.ModifyClientRequest;
import me.javaroad.oauth.dto.response.ClientResponse;
import me.javaroad.oauth.entity.GrantType;
import me.javaroad.oauth.service.ClientService;
import me.javaroad.web.bind.annotation.CurrentUser;
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
@RequestMapping(CONSOLE_API_PREFIX + "/clients")
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
