package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.PmsProductCategoryRepository;
import com.example.FYP_backend.Model.CustomerProduct;
import com.example.FYP_backend.Model.PmsAbstractProduct;
import com.example.FYP_backend.Model.PmsProductCategory;
import com.example.FYP_backend.Utils.TimeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cp")
public class CustomerProductController {
    @Resource
    private PmsAbstractProductRepository abstractProductRepo;
    @Resource
    private PmsProductCategoryRepository categoryRepo;

    @RequestMapping(value = "/oneProduct/{id}")
    public CustomerProduct oneProduct(@PathVariable int id){
        PmsAbstractProduct q = abstractProductRepo.findFirstByIdEquals(id);
        return getCustomerProduct(q);
    }

    @RequestMapping(value = "/productsNameLike")
    public List<CustomerProduct> productsNameLike(@RequestParam String name){
        List<PmsAbstractProduct> qList =  abstractProductRepo.findAllByNameLike(name);
        return getCustomerProducts(qList);
    }

    @RequestMapping(value = "/findHotSale")
    public List<CustomerProduct> findHotSale(){
        List<PmsAbstractProduct> qList =  abstractProductRepo.findAllBySaleGreaterThanEqual(30);
        return getCustomerProducts(qList);
    }

    @RequestMapping(value = "/findNew")
    public List<CustomerProduct> findNew(){
        List<PmsAbstractProduct> qList =  abstractProductRepo.findAllByCreatedTimeGreaterThanEqual(TimeUtils.aMonthDdayAgo());
        return getCustomerProducts(qList);
    }

    @RequestMapping(value = "/productsByCategory")
    public List<CustomerProduct> productsByCategory(@RequestParam int id){
        List<PmsAbstractProduct> qList =  abstractProductRepo.findByCategory_IdEquals(id);
        return getCustomerProducts(qList);
    }

    private List<CustomerProduct> getCustomerProducts(List<PmsAbstractProduct> qList) {
        List<CustomerProduct> result = new LinkedList<>();
        for (PmsAbstractProduct q : qList) {
            result.add(getCustomerProduct(q));
        }
        return result;
    }

    private CustomerProduct getCustomerProduct(PmsAbstractProduct q) {
        CustomerProduct customerProduct = new CustomerProduct();
        customerProduct.setId(q.getId());
        customerProduct.setPrice(q.getPrice());
        customerProduct.setPic(q.getPic());
        customerProduct.setCreatedTime(q.getCreatedTime());
        customerProduct.setName(q.getName());
        customerProduct.setSale(q.getSale());
        customerProduct.setIsLow(q.getIsLow());
        customerProduct.setCategory(q.getCategory());
        return customerProduct;
    }

    @RequestMapping(value = "/findCategories")
    public List<PmsProductCategory> findCategories(){
        return categoryRepo.findAll();
    }

}
