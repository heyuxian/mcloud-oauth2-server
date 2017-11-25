package me.javaroad.oauth.config;

import me.javaroad.data.config.JpaAuditingConfig;
import me.javaroad.web.config.DefaultSwaggerConfig;
import me.javaroad.web.exception.DefaultExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author heyx
 */
@Import({
    DefaultSwaggerConfig.class,
    JpaAuditingConfig.class,
    DefaultExceptionHandler.class
})
@Configuration
public class AppConfig {

}

