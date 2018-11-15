package com.prepaWeb.Controllers;

import com.prepaWeb.Components.UserComponent.UserComponent;
import com.prepaWeb.Repositories.UserProfileRepository.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DirectoryController {

    @Autowired
    UserComponent userComponent;

    @RequestMapping(value = { "/directory" }, method = RequestMethod.GET)
    public String home1(Model model) {

        return "/directory";
    }

    @RequestMapping(value = { "/directory/{id}/details" }, method = RequestMethod.GET)
    public String home1(@PathVariable String id, Model model) {

        UserProfile userProfile = userComponent.getUserDetails(id);
        model.addAttribute("user_profile", userProfile);

        return "/student_details";
    }

    @RequestMapping(value = { "/directory/getList" }, method = RequestMethod.GET)
    public String getStudentsListPage(Model model){
        String studentsjson = userComponent.getUserProfileList();

        model.addAttribute("studentsjson", studentsjson);

        return "studentsjson";
    }
}
