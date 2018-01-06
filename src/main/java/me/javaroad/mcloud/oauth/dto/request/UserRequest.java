package me.javaroad.mcloud.oauth.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.mcloud.oauth.entity.User.UserType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author heyx
 */
@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Size(max = 50)
    private String username;
    @NotBlank
    @Size(max = 255)
    private String password;
    @NotNull
    private UserType userType;
    @Size(max = 50)
    private String email;
    @Size(max = 20)
    private String phone;
    @Size(max = 50)
    private String nickName;
    @Size(max = 255)
    private String avatar;
}
