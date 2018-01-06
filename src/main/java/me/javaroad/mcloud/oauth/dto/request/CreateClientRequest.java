package me.javaroad.mcloud.oauth.dto.request;

import java.util.Set;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.mcloud.oauth.entity.GrantType;
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
    @NotBlank
    @Size(max = 255)
    private String clientSecret;
    private String additionalInformation;
    private Integer accessTokenValidity = 0;
    private Integer refreshTokenValidity = 0;
    @NotEmpty
    private Set<String> redirectUri;
    private Set<GrantType> grantTypes;
    private Set<Long> resourceIds;
    @NotEmpty
    private Set<Long> scopeIds;
    private Set<Long> autoApproveIds;
}
