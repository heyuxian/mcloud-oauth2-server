package me.javaroad.mcloud.oauth.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import me.javaroad.mcloud.oauth.entity.Company;
import me.javaroad.mcloud.oauth.entity.DeveloperInfo.DeveloperType;
import me.javaroad.mcloud.oauth.entity.Domain;
import me.javaroad.mcloud.oauth.entity.Personal;
import me.javaroad.mcloud.oauth.entity.Status;

@Getter
@Setter
public class DeveloperInfoRequest {
    private Long id;
    @NotNull
    @Valid
    private Domain domain;
    @Valid
    private Personal personal;
    @Valid
    private Company company;
    @NotNull
    private DeveloperType type;
    private Status status;
}
