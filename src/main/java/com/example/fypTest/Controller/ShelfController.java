
package com.example.fypTest.Controller;

import com.example.fypTest.Model.SmsShelf;
import com.example.fypTest.Model.SmsShelfItem;
import com.example.fypTest.service.ShelfItemsService;
import com.example.fypTest.service.ShelfService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/sms")
public class ShelfController {

    private ShelfItemsService itemsService;
    private ShelfService shelfService;


    @RequestMapping(value = "/shelfBasicInfo")
    public SmsShelf getBasicInfo(int shelfId){
        return shelfService.getBasicInfo(shelfId);
//        return Shelf.findByIdEquals(10);
    }

    @RequestMapping(value = "/getItems")
    public List<SmsShelfItem> getItems(int shelfId){
//        return itemsService.findItemsByShelfId(shelfId);
        return itemsService.findItemsByShelfId(10);

    }
    @RequestMapping(value = "/itemsSaving")
    public void saveShelf(@RequestBody List<SmsShelfItem> items){
        itemsService.saveItems(items);
    }
}
