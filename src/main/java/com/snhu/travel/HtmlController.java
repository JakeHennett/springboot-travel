package com.snhu.travel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/customlogin")
public class HtmlController {

    //Mimicked from https://www.baeldung.com/spring-controllers
    @GetMapping
    public ModelAndView getLoginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customlogin");
        mv.getModel().put("data", "Login page");

        return mv;
    }

    public String index(Model model){
        return "index";
        //Get more details here https://www.youtube.com/watch?v=cLJ7pSmOdXA&ab_channel=SNHUMedia
        //Part 2 link: https://www.youtube.com/watch?v=iqfy8osrUaI&ab_channel=SNHUMedia
    }

    public String customlogin(Model model){
        return "customlogin";
        //Get more details here https://www.youtube.com/watch?v=cLJ7pSmOdXA&ab_channel=SNHUMedia
        //Part 2 link: https://www.youtube.com/watch?v=iqfy8osrUaI&ab_channel=SNHUMedia
    }
}
