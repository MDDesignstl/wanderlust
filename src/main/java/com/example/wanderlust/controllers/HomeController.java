package com.example.wanderlust.controllers;

import com.example.wanderlust.models.User;
import com.example.wanderlust.models.UserSession;
import com.example.wanderlust.models.forms.UserSignInForm;
import com.example.wanderlust.models.forms.UserSignUpForm;
import com.example.wanderlust.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserDao userDao;
    private Object customErrors;
    private Object errors;

    //home page

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayIndex(HttpSession session,
                               Model model) {

        if(session.getAttribute("currentUser") != null) {
            return "redirect:/wander";
        }

        model.addAttribute("title", "WanderLust");
        model.addAttribute(new UserSignInForm());

        return "index";
    }

    //Post request after login

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLogin(HttpSession session,
                               @ModelAttribute @Valid UserSignInForm newUserSignInForm,
                               Errors errors,
                               Model model) {
        model.addAttribute("title", "WanderLust");

        if(errors.hasErrors()){
            return "index";
        }

       List<User> users = (List<User>) userDao.findAll();

       int currentUserId = 0;



       for (User user : users) {
           if (user.getUsername().equals(newUserSignInForm.getUsername())) {
               currentUserId =  user.getId();
               break;
           }
       }

       if (currentUserId == 0) {
           model.addAttribute("InvalidLogin", "Invalid Login");
           return "index";
       } else {
           User currentUser = userDao.findOne(currentUserId);

           if (BCrypt.checkpw(newUserSignInForm.getPassword(), currentUser.getPassword())) {
                UserSession userSession = new UserSession(currentUser.getId(),
                        currentUser.getUsername(),
                        currentUser.getEmail());
                session.setAttribute("currentUser", userSession);
               return "redirect:/wander";
           }
       }

        model.addAttribute("InvalidLogin", "Invalid Login");
        return "index";
    }

    @RequestMapping(value = "logout")
    public String processLogout(HttpSession session,
                                Model model){
        session.removeAttribute("currentUser");
        return "redirect:";
    }

    //display signup page

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displayAddUserForm(HttpSession session,
                                     Model model) {

        if(session.getAttribute("currentUser") != null) {
            return "redirect:/wander";
        }

        model.addAttribute("title", "WanderLust");
        model.addAttribute(new UserSignUpForm());
        return "signup";
    }

    //process signup page

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processAddUserForm(HttpSession session,
                                     @ModelAttribute @Valid UserSignUpForm newUserSignUpForm,
                                       Errors errors,
                                       Model model) {

        List<User> users = (List<User>) userDao.findAll();
        Boolean errorFlag = false;
        String passwordMatchError = "";
        String uniqueUsernameError = "";

        for (User user : users) {
            if (newUserSignUpForm.getUsername().equals(user.getUsername())) {
                errorFlag = true;
                uniqueUsernameError = "Username already taken";
                break;
            }
        }

        //TODO: Check DB to see if username or email exists


        if(!newUserSignUpForm.getPassword().equals(newUserSignUpForm.getRetypePassword())){
            errorFlag = true;
            passwordMatchError = "Passwords must match";
        }


        if (errors.hasErrors() || errorFlag) {
            model.addAttribute("title", "WanderLust");
            model.addAttribute("passwordMatchError", passwordMatchError);
            model.addAttribute("uniqueUsernameError", uniqueUsernameError);
            return "signup";
        }
        //Assign fields to new user object
        User newUser = new User();
        newUser.setUsername(newUserSignUpForm.getUsername());
        //Hash the password before it goes into the DB
        newUser.setPassword(BCrypt.hashpw(newUserSignUpForm.getPassword(),BCrypt.gensalt()));
        System.out.println(newUser.getPassword());
        newUser.setEmail(newUserSignUpForm.getEmail());
        //Persist user to DB
        userDao.save(newUser);

        UserSession userSession = new UserSession(newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail());
        session.setAttribute("currentUser", userSession);

        return "redirect:/wander";
    }

}
