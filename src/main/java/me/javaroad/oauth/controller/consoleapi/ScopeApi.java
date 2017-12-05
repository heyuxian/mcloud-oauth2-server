package me.javaroad.oauth.controller.consoleapi;

import static me.javaroad.oauth.controller.OAuthConstants.CONSOLE_API_PREFIX;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import me.javaroad.oauth.dto.response.ScopeResponse;
import me.javaroad.oauth.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(CONSOLE_API_PREFIX + "/scopes")
public class ScopeApi {

    private final ScopeService scopeService;

    @Autowired
    public ScopeApi(ScopeService scopeService) {
        this.scopeService = scopeService;
    }

    @ApiOperation(value = "Get All Resources", httpMethod = "GET")
    @GetMapping
    public List<ScopeResponse> getAllScopes() {
        return scopeService.getAll();
    }

}
