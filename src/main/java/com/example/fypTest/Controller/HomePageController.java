
package com.example.fypTest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomePageController {


    @GetMapping("/")
    public String handleJumpDefault() throws Exception{
        return "homePage";
    }

    @GetMapping("/HomePage")
    public String handleJumpHomePage() throws Exception{
        return "homePage";
    }


}
