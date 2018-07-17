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
import org.springframework.web.client.HttpStatusCodeException;

@Controller
@RequestMapping("api")
public class ApiController {

    @Autowired
    private UserDao userDao;
    private FavoriteDao favoriteDao;

    @RequestMapping(value = "addFavorite", method = RequestMethod.GET)
    public String addFavorite(@RequestParam("name") String name,
                              @RequestParam("place_id") String place_id,
                              @RequestParam("lng") String lng,
                              @RequestParam("lat") String lat,
                              @RequestParam("userId") int userId,
                              Model model) {

        User currentUser = userDao.findOne(userId);

        Favorite newFavorite = new Favorite(name,
                place_id,
                lng,
                lat,
                currentUser);

        favoriteDao.save(newFavorite);

        currentUser.addFavorite(newFavorite);

        userDao.save(currentUser);

        return "{\n\"message\": \"Favorites Updated\"\n}";
    }
}
