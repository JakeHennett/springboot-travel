package com.snhu.travel;

import org.springframework.ui.Model;

public class HtmlController {
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
