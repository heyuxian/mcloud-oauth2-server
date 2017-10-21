package me.javaroad.oauth.controller.api;

import static me.javaroad.oauth.controller.OAuthConstants.API_PREFIX;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import me.javaroad.oauth.dto.request.SearchUserRequest;
import me.javaroad.oauth.dto.request.UserRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyx
 */
@RestController
@RequestMapping(API_PREFIX + "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create user", httpMethod = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid UserRequest userRequest) {
        return userService.createOrUpdate(userRequest);
    }

    @ApiOperation(value = "Get user page", httpMethod = "GET")
    @GetMapping
    public Page<UserResponse> getUserPage(SearchUserRequest searchUserRequest,
        @PageableDefault Pageable pageable) {
        return userService.getAll(searchUserRequest, pageable);
    }

    @ApiOperation(value = "Get user", httpMethod = "GET")
    @GetMapping("{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return userService.get(userId);
    }

    @ApiOperation(value = "Delete user", httpMethod = "DELETE")
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
