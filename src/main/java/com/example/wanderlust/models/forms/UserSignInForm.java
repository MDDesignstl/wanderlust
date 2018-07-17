package com.example.wanderlust.models.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserSignInForm {

    @NotNull
    @Size(min=3, max=15, message = "You must enter a Username")
    private String username;

    @NotNull
    @Size(min=3, max=15, message = "You must enter a Password")
    private String password;

    public UserSignInForm() {
    }

    public UserSignInForm(String username, String password) {
        this.username = username;
        this.password = password;
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

}
