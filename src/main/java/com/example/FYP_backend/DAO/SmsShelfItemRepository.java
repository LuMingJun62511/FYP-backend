package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.SmsShelfItem;
import com.example.FYP_backend.Model.SmsShelfItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SmsShelfItemRepository extends JpaRepository<SmsShelfItem, SmsShelfItemId> {
    @Query("select s from SmsShelfItem s where s.shelf.id = ?1")
    List<SmsShelfItem> findByShelf_IdEquals(Integer id);
    @Transactional
    void deleteById_ShelfIdEquals(Integer shelfId);

    SmsShelfItem findFirstById(SmsShelfItemId smsShelfItemId);
}
