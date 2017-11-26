package me.javaroad.oauth.controller.adminapi;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_API_PREFIX;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import me.javaroad.oauth.dto.request.ResourceRequest;
import me.javaroad.oauth.dto.response.ResourceResponse;
import me.javaroad.oauth.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(ADMIN_API_PREFIX + "/resources")
public class AdminResourceApi {

    private final ResourceService resourceService;

    @Autowired
    public AdminResourceApi(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ApiOperation(value = "Create Resource", httpMethod = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceResponse createResource(@RequestBody @Valid ResourceRequest resourceRequest) {
        return resourceService.create(resourceRequest);
    }

    @ApiOperation(value = "Get Resources", httpMethod = "GET")
    @GetMapping
    public Page<ResourceResponse> getResourcePage(@PageableDefault Pageable pageable) {
        return resourceService.getPage(pageable);
    }
}
