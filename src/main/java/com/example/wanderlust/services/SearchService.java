package com.example.wanderlust.services;

import com.example.wanderlust.WanderConfig;
import com.example.wanderlust.models.GooglePlacesApiResponse;
import com.example.wanderlust.models.Location;
import com.example.wanderlust.models.Photo;
import com.example.wanderlust.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SearchService {

    @Autowired
    private static WanderConfig wanderConfig;

    @Autowired
    public void setWanderConfig(WanderConfig wanderConfig) {
        SearchService.wanderConfig = wanderConfig;
    }

    public static Result findRandomLocation(Location location, String wandType){

        List<Result> resultsArray;

        String radius = "5000";
        System.out.println(wandType);

        if (Objects.equals(wandType, "Food")) {
            wandType = "restaurant";
        } else {
            ArrayList<String> tempList = new ArrayList<String>();
            tempList.add("park");
            tempList.add("library");
            tempList.add("museum");

            Random rand = new Random();

            wandType = tempList.get(rand.nextInt(tempList.size()));

        }


        resultsArray = queryForPlaces(location, radius, wandType);

        Random random = new Random();
        int randResult = random.nextInt(resultsArray.size());
        Result theResult = resultsArray.get(randResult);

        System.out.println(theResult);

        return theResult;

    }

    private static List<Result> queryForPlaces(Location location, String radius, String type) {

        String locationString = String.valueOf(location.getLat()) + "," + String.valueOf(location.getLng());

        System.out.println(locationString);

        String randomLocationRequest = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=" +
                wanderConfig.getGoogleApiKey() +
                "&type=" + type +
                "&radius=" + radius +
                "&location=" + locationString;

        System.out.println(randomLocationRequest);

        RestTemplate restTemplate = new RestTemplate();

        GooglePlacesApiResponse locationResults = restTemplate.postForObject(randomLocationRequest, null, GooglePlacesApiResponse.class);

        List<Result> resultsArray = locationResults.getResults();

        if (locationResults.getNextPageToken() != "" || locationResults.getNextPageToken() != null) {
            resultsArray = queryForPlacesNextPage(locationResults.getNextPageToken(), (ArrayList<Result>) resultsArray);
        }

        return resultsArray;
    }

    private static List<Result> queryForPlacesNextPage(String nextPageToken, ArrayList<Result> resultsArray){


        String randomLocationRequest = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=" +
                wanderConfig.getGoogleApiKey() +
                "&pagetoken=" + nextPageToken;

        RestTemplate restTemplate = new RestTemplate();

        GooglePlacesApiResponse locationResults = restTemplate.postForObject(randomLocationRequest, null, GooglePlacesApiResponse.class);

        List<Result> tempResults = locationResults.getResults();

        tempResults.addAll(resultsArray);

        locationResults.setResults(tempResults);

        if (locationResults.getNextPageToken() != null) {
            queryForPlacesNextPage(locationResults.getNextPageToken(), (ArrayList<Result>) locationResults.getResults());
        }

        return locationResults.getResults();

    }

    public static byte[] getPhotoUrl(String maxWidth, String photoReference){

        RestTemplate restTemplate = new RestTemplate();

        String photoRequestUrl ="https://maps.googleapis.com/maps/api/place/photo?" +
                "&maxwidth=" + maxWidth +
                "&photoreference=" + photoReference +
                "&key=" + wanderConfig.getGoogleApiKey();

        return restTemplate.getForObject(photoRequestUrl,byte[].class);

    }

}
