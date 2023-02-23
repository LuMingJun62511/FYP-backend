package com.example.fypTest.service;

import com.example.fypTest.DAO.SmsShelfItemRepository;
import com.example.fypTest.DAO.SmsShelfRepository;
import com.example.fypTest.Model.SmsShelf;
import com.example.fypTest.Model.SmsShelfItem;

import javax.annotation.Resource;
import java.util.List;

public class ShelfItemsService {

    @Resource
    private SmsShelfItemRepository ShelfItems;
    public List<SmsShelfItem> findItemsByShelfId(int shelfId){
        return ShelfItems.findByShelf_IdEquals(shelfId);
    }
    public void saveItems(List<SmsShelfItem> items){
        SmsShelf shelf = items.get(0).getShelf();
        ShelfItems.deleteById_ShelfIdEquals(shelf.getId());
        ShelfItems.saveAll(items);
    }





}
