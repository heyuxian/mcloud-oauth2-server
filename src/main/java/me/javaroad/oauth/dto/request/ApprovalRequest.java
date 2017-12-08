package me.javaroad.oauth.dto.request;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.oauth.entity.Approval.ApprovalStatus;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author heyx
 */
@Getter
@Setter
public class ApprovalRequest {
    @NotBlank
    @Size(max = 50)
    private String name;
    private String description;
    @NotNull
    private Long clientId;
    @NotEmpty
    private Set<Long> scopeIds;
    private ApprovalStatus status;
    private LocalDateTime expiresAt;
}
