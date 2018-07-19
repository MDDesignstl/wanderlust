package com.example.wanderlust.services;

import com.example.wanderlust.WanderConfig;
import com.example.wanderlust.models.GeoLocationResponse;
import com.example.wanderlust.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    @Autowired
    private WanderConfig wanderConfig;

    public Location getLocation() {

        String locationRequest = "https://www.googleapis.com/geolocation/v1/geolocate?key=" +
                wanderConfig.getGoogleApiKey();

        System.out.println(locationRequest);
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<String>(null,headers);

        GeoLocationResponse geoLocationResponse = restTemplate.postForObject(locationRequest, entity, GeoLocationResponse.class);

        Location location = geoLocationResponse.getLocation();

        return location;
    }


}
