package com.prepaWeb.Controllers;

import com.prepaWeb.Components.UserComponent.UserComponent;
import com.prepaWeb.Repositories.UserProfileRepository.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserComponent userComponent;

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String user_register_get(Model model) {

        model.addAttribute("user_profile", new UserProfile());

        return "/register";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String user_register_post(@ModelAttribute("user_profile") UserProfile user_profile, Model model) {

        String redirect = "";

        if(!user_profile.getPassword().equals(user_profile.getCheck_password())){
            redirect = "/register";
        }

        else{
            userComponent.createUser(user_profile);
            redirect = "/login";
        }
        return redirect;
    }

}
