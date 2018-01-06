package me.javaroad.mcloud.oauth.controller.consoleapi;

import javax.validation.Valid;
import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.dto.request.DeveloperInfoRequest;
import me.javaroad.mcloud.oauth.dto.response.DeveloperInfoResponse;
import me.javaroad.mcloud.oauth.entity.DeveloperInfo.DeveloperType;
import me.javaroad.mcloud.oauth.service.DeveloperInfoService;
import me.javaroad.mcloud.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(OAuthConstants.CONSOLE_API_PREFIX + "/dev")
public class DeveloperInfoApi {

    private final DeveloperInfoService developerInfoService;

    @Autowired
    public DeveloperInfoApi(DeveloperInfoService developerInfoService) {
        this.developerInfoService = developerInfoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperInfoResponse apply(@RequestBody @Valid DeveloperInfoRequest request,
        @CurrentUser String username) {

        if (DeveloperType.PERSONAL.equals(request.getType())) {
            validatePersonal(request);
        } else {
            validateCompany(request);
        }
        return developerInfoService.create(username, request);
    }


    private void validateCompany(DeveloperInfoRequest request) {
        Assert.isNull(request.getPersonal(), "Personal info must not be null");
        Assert.notNull(request.getCompany(), "Company info must be null");
    }

    private void validatePersonal(DeveloperInfoRequest request) {
        Assert.notNull(request.getPersonal(), "Personal info must not be null");
        Assert.isNull(request.getCompany(), "Company info must be null");
    }
}
