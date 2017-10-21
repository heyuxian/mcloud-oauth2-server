package me.javaroad.oauth.dto.request;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author heyx
 */
@Getter
@Setter
public class ScopeRequest {
    @NotBlank
    @Size(max = 30)
    private String name;
}
