package com.example.wanderlust.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Favorite {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String name;

    @Column(unique=true)
    @NotNull
    private String  place_id;

    @NotNull
    private String lng;

    @NotNull
    private String lat;

    @NotNull
    @ManyToOne
    private User user;

    public Favorite() {
    }

    public Favorite(String name, String place_id, String lng, String lat, User user) {
        this.name = name;
        this.place_id = place_id;
        this.lng = lng;
        this.lat = lat;
        this.user = user;
    }

    public Favorite(Integer id, String name, String place_id, String lng, String lat, User user) {
        this.id = id;
        this.name = name;
        this.place_id = place_id;
        this.lng = lng;
        this.lat = lat;
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
