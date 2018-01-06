package me.javaroad.mcloud.oauth.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.mcloud.oauth.entity.Approval.ApprovalStatus;

/**
 * @author heyx
 */
@Getter
@Setter
public class ApprovalResponse {
    private String name;
    private Long clientId;
    private Set<Long> scopeIds;
    private ApprovalStatus status;
    private LocalDateTime expiresAt;
}
