package com.example.fypTest.DAO;

import com.example.fypTest.Model.PmsProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PmsProductCategoryRepository extends JpaRepository<PmsProductCategory, Integer> {
    List<PmsProductCategory> findAll();
}
