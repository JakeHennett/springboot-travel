package com.snhu.travel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HtmlController {

    //Prompt user for credentials
    @RequestMapping("/customlogin")
    public ModelAndView customlogin () {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customlogin");
        mv.getModel().put("data", "Login page");

        return mv;
    }
    
    //Prompt user to create an account
    @RequestMapping("/register")
    public ModelAndView register () {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        mv.getModel().put("data", "Registration page");

        return mv;
    }
    
    //Display the interface to request flight details
    @RequestMapping("/flights")
    public ModelAndView flights () {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("flights");
        mv.getModel().put("data", "Flights");

        return mv;
    }

    //May not be necessary. Should test by removing and see if application defaults to login page.
    public String index(Model model){
        return "index";
        //Get more details here https://www.youtube.com/watch?v=cLJ7pSmOdXA&ab_channel=SNHUMedia
        //Part 2 link: https://www.youtube.com/watch?v=iqfy8osrUaI&ab_channel=SNHUMedia
    }

    //May not be necessary. Should test by removing and see if application defaults to login page.
    public String customlogin(Model model){
        return "customlogin";
        //Get more details here https://www.youtube.com/watch?v=cLJ7pSmOdXA&ab_channel=SNHUMedia
        //Part 2 link: https://www.youtube.com/watch?v=iqfy8osrUaI&ab_channel=SNHUMedia
    }
}
