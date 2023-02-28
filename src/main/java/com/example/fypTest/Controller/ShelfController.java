
package com.example.fypTest.Controller;

import com.example.fypTest.DAO.PmsAbstractProductRepository;
import com.example.fypTest.DAO.SmsShelfItemRepository;
import com.example.fypTest.DAO.SmsShelfRepository;
import com.example.fypTest.Model.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


@RestController
@RequestMapping(value = "api/sms")
public class ShelfController {

    @Resource
    private SmsShelfItemRepository ShelfItems;
    @Resource
    private SmsShelfRepository Shelf;
    @Resource
    PmsAbstractProductRepository abstractProductRepo;

    @RequestMapping(value = "/shelves")
    public List<SmsShelf> getAllShelves(){
        return Shelf.findAll();
    }

    @RequestMapping(value = "/shelfBasicInfo/{shelfID}")
    public SmsShelf getBasicInfo(@PathVariable(value = "shelfID") int shelfId){
        return Shelf.findByIdEquals(shelfId);
    }

    @RequestMapping(value = "/getItems/{shelfID}")
    public List<SmsShelfItem> getItems(@PathVariable(value = "shelfID") int shelfId){
        return ShelfItems.findByShelf_IdEquals(shelfId);
    }
    @RequestMapping(value = "/itemsSaving")
    public void saveShelf(@RequestBody List<SmsShelfItem> items){
        SmsShelf shelf = items.get(0).getShelf();
        ShelfItems.deleteById_ShelfIdEquals(shelf.getId());
        ShelfItems.saveAll(items);
//        由于主键的唯一性，所以，他把重复货架id和商品id的项直接给删除了，
//        所以，我这里得注释一下，不允许在货架上放置重复的货物，我觉得这样也挺合理的
    }

    @RequestMapping(value = "/sortByCreated/{shelfID}/{row}/{column}")
    public List<SmsShelfItem> sortByCreated(@RequestBody List<SmsTemp> temps,
                                            @PathVariable(value = "shelfID") int shelfID,
                                            @PathVariable(value = "row") int row,
                                            @PathVariable(value = "column") int column){
        temps = allocateAttr(temps);
        return sortTempsByCreated(temps,row,column,shelfID);
    }

    @RequestMapping(value = "/sortBySales/{shelfID}/{row}/{column}")
    public List<SmsShelfItem> sortBySales(@RequestBody List<SmsTemp> temps,
                                          @PathVariable(value = "shelfID") int shelfID,
                                          @PathVariable(value = "row") int row,
                                          @PathVariable(value = "column") int column){
        temps = allocateAttr(temps);
        return sortTempsBySales(temps,row,column,shelfID);
    }

    @RequestMapping(value = "/sortByBBD/{shelfID}/{row}/{column}")
    public List<SmsShelfItem> sortByBBD(@RequestBody List<SmsTemp> temps,
                                        @PathVariable(value = "shelfID") int shelfID,
                                        @PathVariable(value = "row") int row,
                                        @PathVariable(value = "column") int column){
        temps = allocateAttr(temps);
        return sortTempsByBBD(temps,row,column,shelfID);
    }

    public List<SmsTemp> allocateAttr(List<SmsTemp> temps) {
        for (SmsTemp temp:temps) {
            PmsAbstractProduct pt = abstractProductRepo.findFirstByIdEquals(temp.getId());
            temp.setUrgent(pt.getIsUrgent());
            temp.setCreated(Date.from(pt.getCreatedTime()));
            temp.setSales(pt.getSale());
        }
        return temps;
    }

    public List<SmsShelfItem> sortTempsBySales(List<SmsTemp> temps, int row, int column,int shelfID){
//        Collections.sort(temps, new Comparator<SmsTemp>() {
//            @Override
//            public int compare(SmsTemp temp1, SmsTemp temp2) {
//                return temp1.getSales().compareTo(temp2.getSales());
//            }
//        });
        return shelfSort(temps,row,column,shelfID);
    }

    public List<SmsShelfItem> sortTempsByBBD(List<SmsTemp> temps, int row, int column,int shelfID){
//        Collections.sort(temps, new Comparator<SmsTemp>() {
//            @Override
//            public int compare(SmsTemp temp1, SmsTemp temp2) {
//                return temp1.getSales().compareTo(temp2.getSales());
//            }
//        });
        return shelfSort(temps,row,column,shelfID);
    }

    public List<SmsShelfItem> sortTempsByCreated(List<SmsTemp> temps, int row, int column,int shelfID){
        Collections.sort(temps, new Comparator<SmsTemp>() {
            @Override
            public int compare(SmsTemp temp1, SmsTemp temp2) {
                return temp2.getCreated().compareTo(temp1.getCreated());
            }
//关于这个函数可以再看看，有点高级哦
        });
        return shelfSort(temps,row,column,shelfID);

    }



    public List<SmsShelfItem> shelfSort(List<SmsTemp> temps, int row, int column,int shelfID){
        List<SmsShelfItem> res = new LinkedList<>();
//        先不难为自己，假设是最多一行不满的正常货架， 而且考虑到排设顺序很一致
//        是这样，因为在抽象货物那边掐断了，所以用abstractProductRepo查的效果并不好，不如用ShelfItems查的效果好
        for (int i = column; i<column*2; i++){
            SmsShelfItemId tId = new SmsShelfItemId(shelfID,temps.get(i).getId());
            res.add(ShelfItems.findFirstById(tId));
//            PmsAbstractProduct p1 = abstractProductRepo.findFirstByIdEquals(temps.get(i).getId());
//            p1.setP
//            是这样的，因为我人工截断了死循环，所以我现在得额外查询了，得手动的
//            优先把第二行放满
//            res.add(p1);
        }
        for (int i = 0; i<column; i++){
            SmsShelfItemId tId = new SmsShelfItemId(shelfID,temps.get(i).getId());
            res.add(ShelfItems.findFirstById(tId));
//            再把第一行放满
        }
        for (int i = column*2; i<temps.size(); i++){
            SmsShelfItemId tId = new SmsShelfItemId(shelfID,temps.get(i).getId());
            res.add(ShelfItems.findFirstById(tId));
//            最后把剩下的放满
        }
        return res;
    }

}
