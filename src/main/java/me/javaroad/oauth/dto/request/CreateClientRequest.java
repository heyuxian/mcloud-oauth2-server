package me.javaroad.oauth.dto.request;

import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.oauth.entity.GrantType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author heyx
 */
@Getter
@Setter
public class CreateClientRequest {
    @NotBlank
    @Size(max = 50)
    private String name;
    @Size(max = 255)
    private String clientSecret;
    private String additionalInformation;
    @NotNull
    private Integer accessTokenValidity;
    @NotNull
    private Integer refreshTokenValidity;
    @NotEmpty
    private Set<String> redirectUri;
    @NotEmpty
    private Set<GrantType> grantTypes;
    @NotEmpty
    private Set<Long> resourceIds;
    @NotEmpty
    private Set<Long> scopeIds;
    private Set<Long> autoApproveIds;
}
