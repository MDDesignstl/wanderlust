package com.example.wanderlust;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class WanderConfig {

    @NotNull
    private String googleApiKey;


    public String getGoogleApiKey() {
        return this.googleApiKey;
    }

    public void setGoogleApiKey(String googleApiKey) {
        this.googleApiKey = googleApiKey;
    }

}