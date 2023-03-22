package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.PmsAbstractProductStatisticRepository;
import com.example.FYP_backend.DAO.PmsBatchRepository;
import com.example.FYP_backend.DAO.PmsProductCategoryRepository;
import com.example.FYP_backend.Model.*;
import com.example.FYP_backend.Utils.TimeUtils;
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
    @Resource
    private PmsAbstractProductStatisticRepository productStatisticRepo;

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
    public List<ChartProductData> findProductDataForChart(@PathVariable(value = "productID") int productID){
        List<PmsAbstractProductStatistic> queryList = productStatisticRepo.findAllByIdEquals(productID);
        //先整好8个，
        List<ChartProductData> dataList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {//8周
            dataList.add(new ChartProductData(i-7,0));
        }
        //然后能对上week的就填一条
        for (PmsAbstractProductStatistic p: queryList) {
            int week = TimeUtils.countWeeksBeforeDDay(p.getSaleTime());
            System.out.println(7-week);
            ChartProductData changed = dataList.get(7-week);
            changed.setAmount(changed.getAmount()+p.getAmount());
        }
        return dataList;
    }


}
