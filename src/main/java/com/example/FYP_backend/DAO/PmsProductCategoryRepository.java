package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.PmsProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PmsProductCategoryRepository extends JpaRepository<PmsProductCategory, Integer> {
    List<PmsProductCategory> findAll();
}
