package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.UmsMemberStatisticsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmsMemberStatisticsInfoRepository extends JpaRepository<UmsMemberStatisticsInfo, Integer> {
    UmsMemberStatisticsInfo findByMember_IdEquals(Integer id);

}
