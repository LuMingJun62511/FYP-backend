package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.PmsBatchRepository;
import com.example.FYP_backend.DAO.PmsProductCategoryRepository;
import com.example.FYP_backend.Model.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
@RestController
@RequestMapping(value = "api/pms")
public class ProductController {
    @Resource
    private PmsAbstractProductRepository abstractProductRepo;
    @Resource
    private PmsProductCategoryRepository categoryRepo;
    @Resource
    private PmsBatchRepository batchRepo;
    @RequestMapping(value = "/productsByCategory/{categoryID}")
    public List<PmsAbstractProduct> getMatchedProducts(@PathVariable(value = "categoryID") int categoryID){
        return abstractProductRepo.findByCategory_IdEquals(categoryID);
    }

    @RequestMapping(value = "/productByID/{productID}")
    public PmsAbstractProduct getProduct(@PathVariable(value = "productID") int productID){
        return abstractProductRepo.findFirstByIdEquals(productID);
    }

    @RequestMapping(value = "/productsOfSimilarCategory/{productID}")
    public List<PmsAbstractProduct> getSimilarProducts(@PathVariable(value = "productID") int productID){
        int categoryID =  abstractProductRepo.findFirstByIdEquals(productID).getCategory().getId();
        return abstractProductRepo.findByCategory_IdEquals(categoryID);
    }

    //    根据货物种类找临期商品啥的，第一步，根据货物种类去找，把这类货物全找出来，
//    第二步，排，根据他们的BBD进行排序，然后返回，所以这一步比较简单，然后感觉也不应该放在这里
    @RequestMapping(value = "/findProductsByCreated/{categoryID}")
    public List<PmsAbstractProduct> findByCreated(@PathVariable(value = "categoryID") int categoryID){
        return abstractProductRepo.findAllByIdAndOrderByCreatedTime(categoryID);
    }

    @RequestMapping(value = "/findProductsByBBD/{categoryID}")
    public List<PmsAbstractProduct> findByBBD(@PathVariable(value = "categoryID") int categoryID){
        return abstractProductRepo.findAllByIdAndOrderByBBD(categoryID);
    }

    @RequestMapping(value = "/findProductsBySale/{categoryID}")
    public List<PmsAbstractProduct> findBySale(@PathVariable(value = "categoryID") int categoryID){
        return abstractProductRepo.findAllByIdAndOrderBySale(categoryID);
    }

    @RequestMapping(value = "/findCategories")
    public List<PmsProductCategory> findCategories(){
        return categoryRepo.findAll();
    }
    @RequestMapping(value = "/findBatches/{productID}")
    public List<PmsBatch> findBatches(@PathVariable(value = "productID") int productID){
        return batchRepo.findByAbstractProduct_IdEquals(productID);
    }

    @RequestMapping(value = "/productDataForChart/{productID}")
    public List<ChartOrderData> findProductDataForChart(@PathVariable(value = "productID") int productID){
        List<ChartOrderData> dataList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {//8周
            dataList.add(new ChartOrderData(i-7,0.0,0));
        }

        //然后在统计信息哪个表里找，id对的上的，还得是近段时间的，

        return dataList;
    }
//    @RequestMapping("/orderDataForChart")
//    public List<ChartOrderData> orderData() {
//        List<PmsAbstractProductInfo> queryList = orderRepo.findAllByCreateTimeTwoMonthAgo(eightSettlementDateAgo);
//        for (PmsAbstractProductInfo p: queryList) {
//            int week = countWeeksBeforeNow(p.getCreateTime());
//            ChartOrderData changed = dataList.get(7-week);
//            changed.setSale(changed.getSale()+o.getPayAmount().doubleValue());
//            changed.setAmount(changed.getAmount()+1);
//        }
//        return dataList;
//    }
}
