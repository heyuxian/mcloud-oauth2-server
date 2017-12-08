package me.javaroad.oauth.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author heyx
 */
@Getter
@Setter
@Embeddable
public class Personal {
    @Size(max = 20)
    private String realName;
    @Size(max = 255)
    private String idCardImg;
}
