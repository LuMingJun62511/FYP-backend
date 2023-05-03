package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.PmsAbstractProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface PmsAbstractProductRepository extends JpaRepository<PmsAbstractProduct, Integer> {
    List<PmsAbstractProduct> findByCategory_IdEquals(Integer id);

    @Query("SELECT p from PmsAbstractProduct p WHERE p.category.id = :categoryID AND p.name LIKE %:name% ")
    List<PmsAbstractProduct> findAllByCategory_IdEqualsAndNameLike(Integer categoryID, String name);

    PmsAbstractProduct findFirstByIdEquals(Integer id);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.createdTime DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderByCreatedTime(Integer categoryID);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.isUrgent DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderByBBD(Integer categoryID);

    @Query("SELECT p FROM PmsAbstractProduct p WHERE p.category.id = :categoryID ORDER BY p.sale DESC")
    List<PmsAbstractProduct> findAllByIdAndOrderBySale(Integer categoryID);

    @Query("SELECT p from PmsAbstractProduct p WHERE p.name LIKE %:name% ")
    List<PmsAbstractProduct> findAllByNameLike(String name);

    @Query("SELECT p from PmsAbstractProduct p WHERE p.sale >= :sale")
    List<PmsAbstractProduct> findAllBySaleGreaterThanEqual(Integer sale);

    @Query("SELECT p from PmsAbstractProduct p WHERE p.createdTime >= :createdTime")//新品是一月内的,时间要大于1个月之前的哪个时间点
    List<PmsAbstractProduct> findAllByCreatedTimeGreaterThanEqual(Instant createdTime);
}
