package me.javaroad.oauth.controller.adminapi;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_API_PREFIX;

import io.swagger.annotations.ApiOperation;
import me.javaroad.oauth.dto.response.UserResponse;
import me.javaroad.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(ADMIN_API_PREFIX + "/users")
public class AdminUserApi {

    private final UserService userService;

    @Autowired
    public AdminUserApi(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get user page", httpMethod = "GET")
    @GetMapping
    public Page<UserResponse> getUserPage(@PageableDefault Pageable pageable) {
        return userService.getAll(pageable);
    }

    @ApiOperation(value = "Get user", httpMethod = "GET")
    @GetMapping("{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return userService.getResponse(userId);
    }

    @ApiOperation(value = "Disable user", httpMethod = "DELETE")
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
