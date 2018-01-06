package me.javaroad.mcloud.oauth.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.mcloud.oauth.entity.Company;
import me.javaroad.mcloud.oauth.entity.DeveloperInfo.DeveloperType;
import me.javaroad.mcloud.oauth.entity.Domain;
import me.javaroad.mcloud.oauth.entity.Status;
import me.javaroad.mcloud.oauth.entity.Personal;

@Getter
@Setter
public class DeveloperInfoResponse {
    private Long id;
    private Domain domain;
    private Personal personal;
    private Company company;
    private DeveloperType type;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public boolean isNormal() {
        return Status.NORMAL.equals(status);
    }

    public boolean isPersonal() {
        return DeveloperType.PERSONAL.equals(type);
    }

    public boolean isCompany() {
        return DeveloperType.COMPANY.equals(type);
    }
}
