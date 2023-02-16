package com.example.fypTest.DAO;

import com.example.fypTest.Model.SmsShelfItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsShelfItemRepository extends JpaRepository<SmsShelfItem, Integer> {
    List<SmsShelfItem> findByShelf_IdEquals(Integer id);


}
