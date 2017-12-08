package me.javaroad.oauth.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author heyx
 */
@Getter
@Setter
@Embeddable
public class Domain {
    @NotBlank
    @Size(max = 50)
    private String domain;
    @NotNull
    @Enumerated(EnumType.STRING)
    // todo
    private Protocol protocol = Protocol.HTTP;

    public enum Protocol {
        HTTP, HTTPS
    }
}
