package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.PmsAbstractProductStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PmsAbstractProductStatisticRepository extends JpaRepository<PmsAbstractProductStatistic, Integer> {
//    List<PmsAbstractProductStatistic> findAllByIdEquals(Integer id);

    List<PmsAbstractProductStatistic> findAllByProductIdEquals(Integer id);

}
