package com.kamar.imscli.config;

import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * the proxy configuration class.
 * @author kamar baraka.*/

@Configuration
public class ProxyConfig {

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }


}
