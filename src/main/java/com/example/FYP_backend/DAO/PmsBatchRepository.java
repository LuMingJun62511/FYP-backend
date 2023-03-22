package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.PmsAbstractProduct;
import com.example.FYP_backend.Model.PmsBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PmsBatchRepository extends JpaRepository<PmsBatch, Integer> {
    List<PmsBatch> findByAbstractProduct_IdEquals(Integer id);

    @Query("SELECT b FROM PmsBatch b WHERE b.abstractProduct.id = :productID ORDER BY b.bbd")
    List<PmsBatch> findAllByIdAndOrderByBBD(Integer productID);

    PmsBatch findByIdEquals(Integer id);
}
