package me.javaroad.mcloud.oauth.controller.consoleapi;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.service.ResourceService;
import me.javaroad.mcloud.oauth.dto.response.ResourceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(OAuthConstants.CONSOLE_API_PREFIX + "/resources")
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
