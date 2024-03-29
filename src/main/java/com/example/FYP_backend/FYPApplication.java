package com.example.FYP_backend;

import com.example.FYP_backend.Security.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EntityScan("com.example.FYP_backend.Model")
@EnableJpaRepositories("com.example.FYP_backend.DAO")
public class FYPApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(FYPApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory (){
        // 修改内置的 tomcat 容器配置
        TomcatServletWebServerFactory tomcatServlet = new TomcatServletWebServerFactory();
        tomcatServlet.addConnectorCustomizers(
                (TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}")
        );
        return tomcatServlet ;
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // Make the interceptor works
//        registry.addInterceptor(new LoginInterceptor());
//    }

}
