package com.example.fypTest.Controller;

import com.example.fypTest.DAO.PmsAbstractProductRepository;
import com.example.fypTest.Model.PmsAbstractProduct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping(value = "/pms")
public class ProductController {
    @Resource
    private PmsAbstractProductRepository testAbstractProduct;
    @RequestMapping(value = "/productsAccordingToCategory")
    public List<PmsAbstractProduct> getMatchedProducts(){
        return testAbstractProduct.findByCategory_IdEquals(4);
    }
}
