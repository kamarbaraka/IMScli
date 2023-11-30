package com.kamar.imscli.app_props;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * class containing application properties.
 * @author kamar baraka.*/

@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String resourceServerBaseUrl,
        String authenticationServerBaseUrl,
        String appSignature,
        String innitUsername,
        String innitUserPassword
) {
}
