package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.PmsAbstractProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PmsAbstractProductRepository extends JpaRepository<PmsAbstractProduct, Integer> {
    List<PmsAbstractProduct> findByCategory_IdEquals(Integer id);

    PmsAbstractProduct findFirstByIdEquals(Integer id);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.createdTime DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderByCreatedTime(Integer categoryID);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.isUrgent DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderByBBD(Integer categoryID);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.sale DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderBySale(Integer categoryID);

    @Query("SELECT p from PmsAbstractProduct p WHERE p.name LIKE %:name% ")
    List<PmsAbstractProduct> findAllByNameLike(String name);


}
