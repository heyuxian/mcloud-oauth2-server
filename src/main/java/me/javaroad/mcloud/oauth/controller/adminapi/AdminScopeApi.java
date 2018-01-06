package me.javaroad.mcloud.oauth.controller.adminapi;

import io.swagger.annotations.ApiOperation;
import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.dto.response.ScopeResponse;
import me.javaroad.mcloud.oauth.dto.request.ScopeRequest;
import me.javaroad.mcloud.oauth.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author heyx
 */
@RestController
@RequestMapping(OAuthConstants.ADMIN_API_PREFIX + "/scopes")
public class AdminScopeApi {

    private final ScopeService scopeService;

    @Autowired
    public AdminScopeApi(ScopeService scopeService) {
        this.scopeService = scopeService;
    }

    @ApiOperation(value = "Create Scope", httpMethod = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScopeResponse createScope(@RequestBody @Valid ScopeRequest scopeRequest) {
        return scopeService.createScope(scopeRequest);
    }

    @ApiOperation(value = "Get Resources", httpMethod = "GET")
    @GetMapping
    public Page<ScopeResponse> getScopePage(@PageableDefault Pageable pageable) {
        return scopeService.getPage(pageable);
    }

}
