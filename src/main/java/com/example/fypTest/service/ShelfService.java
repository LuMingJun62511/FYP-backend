package com.example.fypTest.service;

import com.example.fypTest.DAO.SmsShelfRepository;
import com.example.fypTest.Model.SmsShelf;

import javax.annotation.Resource;

public class ShelfService {

    @Resource
    private SmsShelfRepository Shelf;
    public SmsShelf getBasicInfo(int shelfId){
        return Shelf.findByIdEquals(shelfId);
    }
}
