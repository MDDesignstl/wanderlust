package com.example.wanderlust;

import javax.validation.constraints.NotNull;


public class WanderConfig {

    @NotNull
    private String googleApiKey = "AIzaSyAKuho6SiD7I-hkOQNpoWa9j0NntW4Wdz8";

    public void setGoogleApiKey(String googleApiKey) {
        this.googleApiKey = googleApiKey;
    }

    public String getGoogleApiKey() {
        return this.googleApiKey;
    }

}
