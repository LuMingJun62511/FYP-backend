package com.example.fypTest.Controller;

import com.example.fypTest.Model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        System.out.println("admin".equals(user.getUsername()));
        System.out.println("admin".equals(user.getPassword()));

        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            return true;
        }
        return false;
    }

}



