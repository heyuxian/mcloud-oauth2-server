package me.javaroad.oauth.controller.adminapi;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_API_PREFIX;

import io.swagger.annotations.ApiOperation;
import me.javaroad.oauth.dto.request.ScopeRequest;
import me.javaroad.oauth.dto.response.ScopeResponse;
import me.javaroad.oauth.service.ScopeService;
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
@RequestMapping(ADMIN_API_PREFIX + "/scopes")
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
