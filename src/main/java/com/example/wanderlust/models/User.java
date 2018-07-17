package com.example.wanderlust.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique=true)
    @NotNull
    @Size(min=3, max=15, message = "invalid username")
    private String username;

    @NotNull
    @Size(min=3, max=100,message = "invalid Password")
    private String password;

    @Email
    private String email;

    @OneToMany
    private List<Favorite> favorites = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String username, String password, String email, ArrayList<Favorite> favorites) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.favorites = favorites;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Favorite> favorites) { this.favorites = favorites; }

    public void addFavorite(Favorite favorite) {
        this.favorites.add(favorite);
    }
}
