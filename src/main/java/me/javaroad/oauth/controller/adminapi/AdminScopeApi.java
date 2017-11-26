package me.javaroad.oauth.controller.adminapi;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_API_PREFIX;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import me.javaroad.oauth.dto.request.ScopeRequest;
import me.javaroad.oauth.dto.response.ResourceResponse;
import me.javaroad.oauth.dto.response.ScopeResponse;
import me.javaroad.oauth.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author heyx
 */
@Controller
@RequestMapping(ADMIN_API_PREFIX +"/scopes")
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
