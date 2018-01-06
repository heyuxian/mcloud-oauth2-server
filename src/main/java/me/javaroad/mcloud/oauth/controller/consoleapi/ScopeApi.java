package me.javaroad.mcloud.oauth.controller.consoleapi;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.dto.response.ScopeResponse;
import me.javaroad.mcloud.oauth.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(OAuthConstants.CONSOLE_API_PREFIX + "/scopes")
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
