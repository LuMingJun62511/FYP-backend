package com.example.fypTest.DAO;

import com.example.fypTest.Model.PmsAbstractProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PmsAbstractProductRepository extends JpaRepository<PmsAbstractProduct, Integer> {
    List<PmsAbstractProduct> findByCategory_IdEquals(Integer id);

    PmsAbstractProduct findFirstByIdEquals(Integer id);
}
