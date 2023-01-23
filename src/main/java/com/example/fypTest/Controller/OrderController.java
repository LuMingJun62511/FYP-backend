
package com.example.fypTest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OrderController {


    @GetMapping("/OnlineOrder")
    public String handleJumpViewingOrder() throws Exception{
        return "OnlineOrder/orderViewingPage";
    }

    @GetMapping("/OrderHandling")
    public String handleJumpHandlingOrder() throws Exception{
        return "OnlineOrder/orderHandlingPage";
    }



}
