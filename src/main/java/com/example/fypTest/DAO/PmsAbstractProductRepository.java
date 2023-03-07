package com.example.fypTest.DAO;

import com.example.fypTest.Model.PmsAbstractProduct;
import com.example.fypTest.Model.PmsBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PmsAbstractProductRepository extends JpaRepository<PmsAbstractProduct, Integer> {
    List<PmsAbstractProduct> findByCategory_IdEquals(Integer id);
//    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID AND p.id <> :productID")
//    List<PmsAbstractProduct> findByCategory_IdEqualsAndIdNot(Integer categoryID, Integer productID);

    PmsAbstractProduct findFirstByIdEquals(Integer id);
    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.createdTime DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderByCreatedTime(Integer categoryID);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.isUrgent DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderByBBD(Integer categoryID);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.sale DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderBySale(Integer categoryID);




}
