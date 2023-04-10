package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.PmsProductCategoryRepository;
import com.example.FYP_backend.Model.CustomerProduct;
import com.example.FYP_backend.Model.PmsAbstractProduct;
import com.example.FYP_backend.Model.PmsProductCategory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "api/cp")
public class CustomerProductController {
    @Resource
    private PmsAbstractProductRepository abstractProductRepo;
    @Resource
    private PmsProductCategoryRepository categoryRepo;

    @RequestMapping(value = "/productsNameLike")
    public List<CustomerProduct> productsNameLike(@RequestParam String name){
        List<PmsAbstractProduct> qList =  abstractProductRepo.findAllByNameLike(name);
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
            CustomerProduct customerProduct = new CustomerProduct();
            customerProduct.setId(q.getId());
            customerProduct.setPrice(q.getPrice());
            customerProduct.setPic(q.getPic());
            customerProduct.setCreatedTime(q.getCreatedTime());
            customerProduct.setName(q.getName());
            customerProduct.setIsLow(q.getIsLow());
            customerProduct.setCategory(q.getCategory());
            result.add(customerProduct);
        }
        return result;
    }

    @RequestMapping(value = "/findCategories")
    public List<PmsProductCategory> findCategories(){
        return categoryRepo.findAll();
    }

}
