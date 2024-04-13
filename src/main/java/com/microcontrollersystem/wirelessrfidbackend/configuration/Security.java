package com.microcontrollersystem.wirelessrfidbackend.configuration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.jwt")
@Data
public class Security {
    private String secret;
    private String issuer;
    private Long ttlMillis;
}
