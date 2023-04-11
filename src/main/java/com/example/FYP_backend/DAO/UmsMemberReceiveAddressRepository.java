package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.UmsMemberReceiveAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UmsMemberReceiveAddressRepository extends JpaRepository<UmsMemberReceiveAddress, Integer> {
    List<UmsMemberReceiveAddress> findByMemberIdEquals(Integer memberId);

}
