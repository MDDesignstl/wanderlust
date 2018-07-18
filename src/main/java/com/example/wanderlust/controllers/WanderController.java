package com.example.wanderlust.controllers;

import com.example.wanderlust.models.*;
import com.example.wanderlust.models.data.FavoriteDao;
import com.example.wanderlust.models.data.UserDao;
import com.example.wanderlust.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.wanderlust.services.LocationService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("wander")
public class WanderController {

    @Autowired
    FavoriteDao favoriteDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpSession session,
                        Model model){

        if(session.getAttribute("currentUser") != null) {
            model.addAttribute("currentUser", session.getAttribute("currentUser"));
        }

        return "wander/index";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String result(HttpSession session,
                         Model model,
                         @RequestParam String wandType){

        if(session.getAttribute("currentUser") != null) {
            model.addAttribute("currentUser", session.getAttribute("currentUser"));
        }

        Location location = LocationService.getLocation();

        Result result = SearchService.findRandomLocation(location, wandType);

        /*if(result.getPhotos().get(0) != null) {
            byte[] photo = SearchService.getPhotoUrl("400", result.getPhotos().get(0).getPhotoReference());
            model.addAttribute("Photo", Base64.getEncoder().encode(photo));
        }*/

        //create directions url

        try {
            String directionsUrl = "https://www.google.com/maps/dir/?api=1&dir_action=navigate"
            + "&destination=" + URLEncoder.encode(result.getName(),"UTF-8")
            + "&destination_place_id=" + result.getPlaceId();

            model.addAttribute("directionsUrl",directionsUrl);
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }


        model.addAttribute("Location", location);
        model.addAttribute("Result",result);

        return "wander/index";

    }

    @RequestMapping(value = "favorites", method = RequestMethod.GET)
    public String favorites(HttpSession session,
                        Model model){

        UserSession currentUser = new UserSession();

        if(session.getAttribute("currentUser") == null) {
            return "redirect:../";
        } else {
            currentUser = (UserSession) session.getAttribute("currentUser");
            model.addAttribute("currentUser", currentUser);
        }

        ArrayList<Favorite> allFavorites = (ArrayList<Favorite>) favoriteDao.findAll();
        ArrayList<Favorite> userFavorites = new ArrayList<>();

        for ( Favorite favorite : allFavorites) {
            if (favorite.getUser().getId().equals(currentUser.getUserId())) {
                userFavorites.add(favorite);
                }
            }

        model.addAttribute("Results", userFavorites);

        return "wander/favorites";

    }


}
