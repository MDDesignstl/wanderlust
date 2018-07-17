package com.example.wanderlust.models.forms;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserSignUpForm {

    @NotNull
    @Size(min=3, max=15, message = "You must enter a Username")
    private String username;

    @NotNull
    @Size(min=3, max=15, message = "You must enter a Password")
    private String password;

    @NotNull
    @Size(min=3, max=15, message = "You must enter a Password")
    private String retypePassword;

    @Email
    private String email;

    public UserSignUpForm() {
    }

    public UserSignUpForm(String username, String password, String retypePassword, String email) {
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
        this.email = email;
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

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
