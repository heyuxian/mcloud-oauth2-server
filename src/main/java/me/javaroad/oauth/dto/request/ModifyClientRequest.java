package me.javaroad.oauth.dto.request;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author heyx
 */
@Getter
@Setter
public class ModifyClientRequest {
    private String clientSecret;
    @NotEmpty
    private Set<String> redirectUri;
}
