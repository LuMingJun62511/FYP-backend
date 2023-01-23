
package com.example.fypTest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IOboundController {


    @GetMapping("/InAndOutbound")
    public String handleJumpOrder() throws Exception{
        return "InAndOutbound/tempPage";
    }



}
