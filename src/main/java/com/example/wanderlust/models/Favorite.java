package com.example.wanderlust.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Entity
public class Favorite {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String  place_id;

    private Integer priceLevel;

    private Double rating;

    @ManyToOne
    private User user;

    public Favorite() {
    }

    public Favorite(Integer id, String name, String place_id, Integer priceLevel, Double rating, User user) {
        this.id = id;
        this.name = name;
        this.place_id = place_id;
        this.priceLevel = priceLevel;
        this.rating = rating;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDirectionsUrl() throws UnsupportedEncodingException {

        return "https://www.google.com/maps/dir/?api=1&dir_action=navigate" +
                "&destination=" + URLEncoder.encode(this.name,"UTF-8") +
                "&destination_place_id=" + this.place_id;

    }
}
