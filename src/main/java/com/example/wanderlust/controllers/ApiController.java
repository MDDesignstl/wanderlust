package com.example.wanderlust.controllers;

import com.example.wanderlust.models.Favorite;
import com.example.wanderlust.models.User;
import com.example.wanderlust.models.data.FavoriteDao;
import com.example.wanderlust.models.data.UserDao;
import com.example.wanderlust.models.forms.UserSignInForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FavoriteDao favoriteDao;

    @RequestMapping(value = "addFavorite", method = RequestMethod.GET)
    public String addFavorite(@RequestParam("name") String name,
                              @RequestParam("placeId") String placeId,
                              @RequestParam("userId") int userId,
                              Model model) {

        User currentUser = userDao.findOne(userId);


        //TODO: check to see if favorite is a duplicate for user

        Favorite newFavorite = new Favorite();
        newFavorite.setName(name);
        newFavorite.setPlace_id(placeId);
        newFavorite.setUser(currentUser);

        favoriteDao.save(newFavorite);

        return "{\n\"message\": \"Favorites Updated\"\n}";
    }
}
