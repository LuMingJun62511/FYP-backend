package com.example.fypTest.Controller;

import com.example.fypTest.DAO.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller

public class UserController {
    @Autowired
    UserEntityRepository testUser;
    @GetMapping("/userInfo")
    public String getUserEmail(HttpSession session) throws Exception {
        System.out.println(testUser.findByIdEquals(1).getEmail());
        session.setAttribute("email", testUser.findByIdEquals(1).getEmail());
        return "homePage";
//        return "t1";
    }


//    这是在测试,我在前端,主动点击了一个跳转,然后,在后端,我接收到了这个跳转,之后,我应该安排他跳转到对应的页面去
//    @GetMapping("/test")
//    public String handleJumpTest() throws Exception{
//        return "user/userManage";
//    }



//    @GetMapping("/CommodityInfo")
//    public String handleJumpCommodityInfo() throws Exception{
//        return "commodityManagement/CMPage";
//    }

//    @GetMapping("/CommodityInWarehouse")
//    public String handleJumpCommodityInWarehouse() throws Exception{
//        return "commodityManagement/CMPage";
//    }
//
//    @GetMapping("/ShelfManagement")
//    public String handleJumpShelfManagement() throws Exception{
//        return "commodityManagement/CMPage";
//    }
//
//    @GetMapping("/OnlineOrder")
//    public String handleJumpOnlineOrder() throws Exception{
//        return "commodityManagement/CMPage";
//    }
//
//    @GetMapping("/Inbound")
//    public String handleJumpInbound() throws Exception{
//        return "commodityManagement/CMPage";
//    }
//
//    @GetMapping("/Outbound")
//    public String handleJumpOutbound() throws Exception{
//        return "commodityManagement/CMPage";
//    }
//
//    @GetMapping("/CustomersManagement")
//    public String handleJumpCustomersManagement() throws Exception{
//        return "commodityManagement/CMPage";
//    }

}
