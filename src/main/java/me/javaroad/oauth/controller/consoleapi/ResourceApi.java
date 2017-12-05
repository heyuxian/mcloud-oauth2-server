package me.javaroad.oauth.controller.consoleapi;

import static me.javaroad.oauth.controller.OAuthConstants.CONSOLE_API_PREFIX;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import me.javaroad.oauth.dto.response.ResourceResponse;
import me.javaroad.oauth.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(CONSOLE_API_PREFIX + "/resources")
public class ResourceApi {

    private final ResourceService resourceService;

    @Autowired
    public ResourceApi(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @ApiOperation(value = "Get All Resources", httpMethod = "GET")
    @GetMapping
    public List<ResourceResponse> getAllResources() {
        return resourceService.getAll();
    }
}
