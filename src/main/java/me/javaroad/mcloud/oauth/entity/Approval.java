package me.javaroad.mcloud.oauth.entity;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.data.entity.TemporalEntity;

/**
 * @author heyx
 */
@Getter
@Setter
@Entity
@Table(name = "oauth_approval")
public class Approval extends TemporalEntity {
    @Column(unique = true)
    private String name;
    private String description;
    @ManyToOne
    private User user;
    @ManyToOne
    private Client client;
    @ManyToMany
    @JoinTable(name = "approval_scope",
        joinColumns = @JoinColumn(name = "approve_id"),
        inverseJoinColumns = @JoinColumn(name = "scope_id")
    )
    private Set<Scope> scope;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;
    private LocalDateTime expiresAt;

    public enum ApprovalStatus {
        APPROVED,
        DENIED;
    }
}
