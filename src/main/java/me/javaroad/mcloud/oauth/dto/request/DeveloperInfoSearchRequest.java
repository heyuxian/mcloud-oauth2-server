package me.javaroad.mcloud.oauth.dto.request;

import lombok.Getter;
import lombok.Setter;
import me.javaroad.mcloud.oauth.entity.Status;

@Getter
@Setter
public class DeveloperInfoSearchRequest {
    private Status status;
}
