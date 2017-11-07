package me.javaroad.oauth.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author heyx
 */
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String nickName;
    private String avatar;
}
