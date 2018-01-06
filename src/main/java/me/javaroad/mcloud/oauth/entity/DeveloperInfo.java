package me.javaroad.mcloud.oauth.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
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
@Table(name = "developer_info")
public class DeveloperInfo extends TemporalEntity {
    @OneToOne
    private User user;
    @Embedded
    private Domain domain;
    @Embedded
    private Personal personal;
    @Embedded
    private Company company;
    @Enumerated(EnumType.STRING)
    private DeveloperType type;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum DeveloperType {
        PERSONAL, COMPANY
    }

}
