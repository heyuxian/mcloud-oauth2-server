package me.javaroad.oauth.controller.adminapi;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_PREFIX;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import me.javaroad.oauth.dto.request.ClientRequest;
import me.javaroad.oauth.dto.response.ClientResponse;
import me.javaroad.oauth.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(ADMIN_PREFIX + "/clients")
public class AdminClientApi {

    private final ClientService clientService;

    @Autowired
    public AdminClientApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Create Client", httpMethod = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse createClient(@RequestBody @Valid ClientRequest clientRequest) {
        return clientService.create(clientRequest);
    }

    @ApiOperation(value = "Delete Client", httpMethod = "DELETE")
    @DeleteMapping("{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long clientId) {
        clientService.delete(clientId);
    }

}
