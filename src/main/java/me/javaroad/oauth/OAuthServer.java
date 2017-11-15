package me.javaroad.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author heyx
 */
//@EnableEurekaClient
@SpringBootApplication
public class OAuthServer {
    public static void main(String[] args) {
        SpringApplication.run(OAuthServer.class, args);
    }

}
