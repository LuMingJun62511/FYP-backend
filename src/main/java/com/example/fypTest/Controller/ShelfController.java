
package com.example.fypTest.Controller;

import com.example.fypTest.DAO.SmsShelfItemRepository;
import com.example.fypTest.DAO.SmsShelfRepository;
import com.example.fypTest.Model.SmsShelfItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/sms")
public class ShelfController {

    @Resource
    private SmsShelfItemRepository testShelfItems;
    @RequestMapping(value = "/ShelfManagement")
    public List<SmsShelfItem> getMatchedRows(){
        return testShelfItems.findByShelf_IdEquals(10);
    }

    @Resource
    private SmsShelfRepository testShelf;
    @RequestMapping(value = "/ShelfBasicInfoRow")
    public int getBasicInfoRow(){
        return testShelf.findByIdEquals(10).getRowNum();
    }

    @RequestMapping(value = "/ShelfBasicInfoCol")
    public int getBasicInfoCol(){
        return testShelf.findByIdEquals(10).getColNum();
    }

}
