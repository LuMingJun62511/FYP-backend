package com.example.fypTest.Controller;

import com.example.fypTest.Model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @PostMapping("/login")
    public boolean login(@RequestBody User user, HttpSession session) {
//        System.out.println(user);
        System.out.println("admin".equals(user.getUsername()));
        System.out.println("admin".equals(user.getPassword()));

        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            // 正确的话就将用户信息存到session中
            session.setAttribute("user", user);
            return true;
        }
        return false;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 退出登录就是将用户信息删除
        session.removeAttribute("user");
        return "退出成功";
    }
//    所以我需要给所有东西加一个统一前缀，
    @GetMapping("api")
    public String api(HttpSession session) {
        // 如果有登录就调用业务层执行业务逻辑，然后返回数据
        return "成功返回数据";
    }

}



