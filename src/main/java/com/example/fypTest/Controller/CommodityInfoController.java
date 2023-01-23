package com.example.fypTest.Controller;

import com.example.fypTest.DAO.CommodityTestEntityRepository;
import com.example.fypTest.DAO.UserEntityRepository;
import com.example.fypTest.Model.CommodityTestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommodityInfoController {
    @Autowired
    CommodityTestEntityRepository commodityTest;
//    去仓库页面
    @GetMapping("/CommodityManagement")
    public ModelAndView handleJumpAllCommodity() throws Exception {
        ModelAndView m1 = new ModelAndView();
        List<CommodityTestEntity> commoList =  commodityTest.findAll();
        m1.addObject("commoList", commoList);
        m1.setViewName("commodityManagement/warehousePage");
        return m1;
    }

    @RequestMapping("/CommodityInfo")
    public ModelAndView handleJumpCertainCommodity(int commoID) throws Exception {
        System.out.println();
        ModelAndView m1 = new ModelAndView();
        m1.setViewName("commodityManagement/specificCommodityPage");
        m1.addObject("commo", commodityTest.findByIdEquals(commoID));
        return m1;
    }
}
