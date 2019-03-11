package com.cashback.client.spotify;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class SpotifyApiClientBuilder {

    @Autowired
    private SpopifyApiConfig spopifyApiConfig;

    @Bean
    public SpotifyApiClient build() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(new RequestJsonInterceptor(spopifyApiConfig.getAuthorization()))
                .target(SpotifyApiClient.class, spopifyApiConfig.getUrl());
    }

    public class RequestJsonInterceptor implements RequestInterceptor {

        private final String authorization;

        RequestJsonInterceptor(String authorization) {
            this.authorization = authorization;
        }

        @Override
        public void apply(RequestTemplate template) {
            template.header("Authorization", authorization);
            template.header("Accept:","application/json");
            template.header("Content-Type:","application/json");
        }
    }
}
