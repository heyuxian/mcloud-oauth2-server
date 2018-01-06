package me.javaroad.mcloud.oauth.controller.adminapi;

import io.swagger.annotations.ApiOperation;
import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.dto.response.ClientResponse;
import me.javaroad.mcloud.oauth.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(OAuthConstants.ADMIN_API_PREFIX + "/clients")
public class AdminClientApi {

    private final ClientService clientService;

    @Autowired
    public AdminClientApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Delete Client", httpMethod = "DELETE")
    @DeleteMapping("{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long clientId) {
        clientService.delete(clientId);
    }

    @ApiOperation(value = "Get Clients", httpMethod = "GET")
    @GetMapping
    public Page<ClientResponse> getClientPage(@PageableDefault Pageable pageable) {
        return clientService.getPage(pageable);
    }

}
