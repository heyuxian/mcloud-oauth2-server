package me.javaroad.oauth.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.oauth.entity.Company;
import me.javaroad.oauth.entity.DeveloperInfo.DeveloperType;
import me.javaroad.oauth.entity.Domain;
import me.javaroad.oauth.entity.Personal;
import me.javaroad.oauth.entity.Status;

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
