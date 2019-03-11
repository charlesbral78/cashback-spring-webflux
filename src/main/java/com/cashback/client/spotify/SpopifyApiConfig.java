package com.cashback.client.spotify;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spopify-api-config")
public class SpopifyApiConfig {

    private String url;
    private String authorization;
    private Integer limit;
}
