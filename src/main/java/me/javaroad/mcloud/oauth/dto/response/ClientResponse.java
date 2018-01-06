package me.javaroad.mcloud.oauth.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.mcloud.oauth.entity.GrantType;
import me.javaroad.mcloud.oauth.entity.Status;

/**
 * @author heyx
 */
@Getter
@Setter
public class ClientResponse {
    private Long id;
    private String clientId;
    @JsonIgnore
    private String clientSecret;
    private String name;
    private Set<ResourceResponse> resources;
    private Set<ScopeResponse> scope;
    private Set<GrantType> grantTypes;
    private Set<String> redirectUri;
    private Set<AuthorityResponse> authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private Set<ApprovalResponse> autoApprove;
    private Status status;
}
