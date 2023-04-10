package com.example.FYP_backend.Security;

import com.example.FYP_backend.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // White list: obviously login request does not need to verify authorization
        if ("/login".equals(request.getRequestURI())) {
            return true;
        }

        // Get the token string from the request header and parse it
        Claims claims = JwtUtil.parse(request.getHeader("Authorization"));
        // If user have logged in, user will be released directly
        if (claims != null) {
            return true;
        }

        // Arrive at here means that user not login
        // Set the response data type to json (separation of front and back ends)
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("please login first");
        out.flush();
        out.close();
        return false;
    }
}
