
package com.example.fypTest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShelfController {


    @GetMapping("/ShelfManagement")
    public String handleJumpShelf() throws Exception{
        return "ShelfManagement/shelfManagementPage";
    }



}
