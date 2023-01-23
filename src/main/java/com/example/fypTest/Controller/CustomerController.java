
package com.example.fypTest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CustomerController {


    @GetMapping("/CustomersManagement")
    public String handleJumpShelf() throws Exception{
        return "CustomerManagement/tempPage";
    }



}
