
package com.example.fypTest.Controller;

import com.example.fypTest.DAO.SmsShelfItemRepository;
import com.example.fypTest.DAO.SmsShelfRepository;
import com.example.fypTest.Model.SmsShelf;
import com.example.fypTest.Model.SmsShelfItem;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/sms")
public class ShelfController {

    @Resource
    private SmsShelfItemRepository ShelfItems;
    @RequestMapping(value = "/ShelfManagement")
    public List<SmsShelfItem> getMatchedRows(){
        return ShelfItems.findByShelf_IdEquals(10);
    }

    @Resource
    private SmsShelfRepository Shelf;
    @RequestMapping(value = "/ShelfBasicInfo")
    public SmsShelf getBasicInfo(){
        return Shelf.findByIdEquals(10);
    }
    @RequestMapping(value = "/shelfSaving")
    public void saveShelf(@RequestBody List<SmsShelfItem> items){
        SmsShelf shelf = items.get(0).getShelf();
        ShelfItems.deleteById_ShelfIdEquals(shelf.getId());
        ShelfItems.saveAll(items);
    }
}
