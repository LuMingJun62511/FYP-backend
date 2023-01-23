package com.example.fypTest.DAO;

import com.example.fypTest.Model.CommodityTestEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityTestEntityRepository extends JpaRepository<CommodityTestEntity, Integer> {
    CommodityTestEntity findByIdEquals(Integer id);

    List<CommodityTestEntity> findAll();
}
