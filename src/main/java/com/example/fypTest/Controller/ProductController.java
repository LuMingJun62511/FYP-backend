package com.example.fypTest.Controller;

import com.example.fypTest.DAO.PmsAbstractProductRepository;
import com.example.fypTest.Model.PmsAbstractProduct;
import com.example.fypTest.Model.SmsShelfItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping(value = "api/pms")
public class ProductController {
    @Resource
    private PmsAbstractProductRepository testAbstractProduct;
    @RequestMapping(value = "/productsAccordingToCategory")
    public List<PmsAbstractProduct> getMatchedProducts(){
        return testAbstractProduct.findByCategory_IdEquals(4);
    }

    //    根据货物种类找临期商品啥的，第一步，根据货物种类去找，把这类货物全找出来，
//    第二步，排，根据他们的BBD进行排序，然后返回，所以这一步比较简单，然后感觉也不应该放在这里
    @RequestMapping(value = "/findProductsByCreated/{categoryID}")
    public List<PmsAbstractProduct> findByCreated(@PathVariable(value = "categoryID") int categoryID){
        return testAbstractProduct.findAllByIdAndOrderByCreatedTime(categoryID);
    }

    @RequestMapping(value = "/findProductsByBBD/{categoryID}")
    public List<PmsAbstractProduct> findByBBD(@PathVariable(value = "categoryID") int categoryID){
        return testAbstractProduct.findAllByIdAndOrderByBBD(categoryID);
    }

    @RequestMapping(value = "/findProductsBySale/{categoryID}")
    public List<PmsAbstractProduct> findBySale(@PathVariable(value = "categoryID") int categoryID){
        return testAbstractProduct.findAllByIdAndOrderBySale(categoryID);
    }
}
