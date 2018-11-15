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
public class AccountController {

    @Autowired
    UserComponent userComponent;
    
    @RequestMapping(value = { "/my_account" }, method = RequestMethod.GET)
    public String user_details_get(Model model) {

        String profileId = userComponent.getUserProfileId();
        UserProfile userProfile = userComponent.getUserDetails(profileId);

        model.addAttribute("user_profile", userProfile);

        return "/my_account";
    }

    @RequestMapping(value = { "/my_account/update" }, method = RequestMethod.GET)
    public String user_detailsUpdated_get(Model model) {

        String profileId = userComponent.getUserProfileId();
        UserProfile userProfile = userComponent.getUserDetails(profileId);

        model.addAttribute("user_profile", userProfile);

        return "/my_account";
    }

    @RequestMapping(value = { "/my_account/update" }, method = RequestMethod.POST)
    public String user_register_get(Model model, @ModelAttribute("user_profile") UserProfile user_profile) {

        String profileId = userComponent.getUserProfileId();
        userComponent.updateUserProfile(profileId, user_profile);


        return "/my_account";
    }

}
