package com.example.wanderlust.services;

import com.example.wanderlust.WanderConfig;
import com.example.wanderlust.models.GeoLocationResponse;
import com.example.wanderlust.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        GeoLocationResponse geoLocationResponse = restTemplate.postForObject(locationRequest, null, GeoLocationResponse.class);

        Location location = geoLocationResponse.getLocation();

        return location;
    }


}
