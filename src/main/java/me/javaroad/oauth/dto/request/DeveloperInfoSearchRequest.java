package me.javaroad.oauth.dto.request;

import lombok.Getter;
import lombok.Setter;
import me.javaroad.oauth.entity.Status;

@Getter
@Setter
public class DeveloperInfoSearchRequest {
    private Status status;
}
