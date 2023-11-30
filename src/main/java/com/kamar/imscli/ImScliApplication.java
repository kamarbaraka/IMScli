package com.kamar.imscli;

import com.kamar.imscli.app_props.AppProperties;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppProperties.class})
public class ImScliApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImScliApplication.class, args);
    }

}
