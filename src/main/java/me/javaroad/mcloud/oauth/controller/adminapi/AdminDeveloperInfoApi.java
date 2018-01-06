package me.javaroad.mcloud.oauth.controller.adminapi;

import io.swagger.annotations.ApiOperation;
import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.dto.request.DeveloperInfoSearchRequest;
import me.javaroad.mcloud.oauth.dto.response.DeveloperInfoResponse;
import me.javaroad.mcloud.oauth.entity.Status;
import me.javaroad.mcloud.oauth.service.DeveloperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(OAuthConstants.ADMIN_API_PREFIX + "/developer-infos")
public class AdminDeveloperInfoApi {

    private final DeveloperInfoService developerInfoService;

    @Autowired
    public AdminDeveloperInfoApi(DeveloperInfoService developerInfoService) {
        this.developerInfoService = developerInfoService;
    }

    @ApiOperation(value = "Get developer page", httpMethod = "GET")
    @GetMapping
    public Page<DeveloperInfoResponse> getDeveloperInfoPage(@PageableDefault Pageable pageable,
        DeveloperInfoSearchRequest searchRequest) {
        return developerInfoService.search(searchRequest, pageable);
    }

    @ApiOperation(value = "Get developerInfo", httpMethod = "GET")
    @GetMapping("{developerInfoId}")
    public DeveloperInfoResponse getDeveloperInfo(@PathVariable Long developerInfoId) {
        return developerInfoService.getResponse(developerInfoId);
    }

    @ApiOperation(value = "Review developerInfo", httpMethod = "POST")
    @PostMapping("{developerInfoId}/review/{status}")
    public void review(@PathVariable Long developerInfoId, @PathVariable Status status) {
        developerInfoService.review(developerInfoId, status);
    }
}
