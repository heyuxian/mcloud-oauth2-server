package me.javaroad.mcloud.oauth.config;

import me.javaroad.mcloud.web.config.SimpleWebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * @author heyx
 */
@Configuration
public class AppWebConfig extends SimpleWebConfig {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
