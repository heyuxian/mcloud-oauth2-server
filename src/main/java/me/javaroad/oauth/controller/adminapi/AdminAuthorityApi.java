package me.javaroad.oauth.controller.adminapi;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_API_PREFIX;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import me.javaroad.oauth.dto.request.AuthorityRequest;
import me.javaroad.oauth.dto.response.AuthorityResponse;
import me.javaroad.oauth.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(ADMIN_API_PREFIX + "/authorities")
public class AdminAuthorityApi {

    private final AuthorityService authorityService;

    @Autowired
    public AdminAuthorityApi(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @ApiOperation(value = "Create Authority", httpMethod = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorityResponse createAuthority(@RequestBody @Valid AuthorityRequest authorityRequest) {
        return authorityService.create(authorityRequest);
    }

    @ApiOperation(value = "Modify Authority", httpMethod = "PUT")
    @PutMapping("{authorityId}")
    public AuthorityResponse modifyAuthority(@PathVariable Long authorityId,
        @RequestBody @Valid AuthorityRequest authorityRequest) {
        return authorityService.modify(authorityId, authorityRequest);
    }
}
