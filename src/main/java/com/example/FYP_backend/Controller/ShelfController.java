
package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.SmsShelfItemRepository;
import com.example.FYP_backend.DAO.SmsShelfRepository;
import com.example.FYP_backend.Model.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


@RestController
@RequestMapping(value = "api/sms")
public class ShelfController {

    @Resource
    private SmsShelfItemRepository shelfItemsRepo;
    @Resource
    private SmsShelfRepository shelfRepo;
    @Resource
    private PmsAbstractProductRepository abstractProductRepo;

    @RequestMapping(value = "/shelves")
    public List<SmsShelf> getAllShelves(){
        return shelfRepo.findAll();
    }

    @RequestMapping(value = "/shelfBasicInfo/{shelfID}")
    public SmsShelf getBasicInfo(@PathVariable(value = "shelfID") int shelfId){
        return shelfRepo.findByIdEquals(shelfId);
    }

    @RequestMapping(value = "/getItems/{shelfID}")
    public List<SmsShelfItem> getItems(@PathVariable(value = "shelfID") int shelfId){
        return shelfItemsRepo.findByShelf_IdEquals(shelfId);
    }
    @RequestMapping(value = "/itemsSaving")
    public void saveItemsInShelf(@RequestBody List<SmsShelfItem> items){
        SmsShelf shelf = items.get(0).getShelf();
        shelfItemsRepo.deleteById_ShelfIdEquals(shelf.getId());
        shelfItemsRepo.saveAll(items);
//        由于主键的唯一性，所以，他把重复货架id和商品id的项直接给删除了，
//        所以，我这里得注释一下，不允许在货架上放置重复的货物，我觉得这样也挺合理的
    }

    @RequestMapping(value = "/createOneShelf")
    public void createOneShelf(@RequestBody SmsShelf shelf){
        shelfRepo.save(shelf);
    }

    @RequestMapping(value = "/deleteShelf/{shelfID}")
    public void deleteShelf(@PathVariable(value = "shelfID") int shelfId){
        shelfItemsRepo.deleteById_ShelfIdEquals(shelfId);
        shelfRepo.deleteById(shelfId);
    }

    @RequestMapping(value = "/autoFillShelf")
    public void autoFillShelf(@RequestBody SmsShelf shelf){//可能不太乐观，因为shelf型实体中含一个category，我传了个int
        //取至多row*col，然后更新shelfItems
        List<SmsShelfItem> res = new LinkedList<>();
        List<PmsAbstractProduct> productList = abstractProductRepo.findByCategory_IdEquals(shelf.getCategoryId()).subList(0,shelf.getColNum()*shelf.getRowNum());
        for (PmsAbstractProduct p:productList){
            res.add(new SmsShelfItem(
                    new SmsShelfItemId(shelf.getId(),p.getId()),
                    shelf,
                    p,
                    shelf.getRowNum(),
                    shelf.getColNum()
            ));
        }
        for (int i=0; i<productList.size();i++){
            res.add(new SmsShelfItem(
                    new SmsShelfItemId(shelf.getId(),productList.get(i).getId()),
                    shelf,
                    productList.get(i),
                    i/shelf.getColNum()+1,
                    i%shelf.getColNum()+1
            ));
        }
        shelfItemsRepo.saveAll(res);
    }

    @RequestMapping(value = "/sortByCreated/{shelfID}/{column}")
    public List<SmsShelfItem> sortByCreated(@RequestBody List<SmsTemp> temps,
                                            @PathVariable(value = "shelfID") int shelfID,
                                            @PathVariable(value = "column") int column){
        temps = allocateAttr(temps);
        return sortTempsByCreated(temps,column,shelfID);
    }

    @RequestMapping(value = "/sortBySales/{shelfID}/{column}")
    public List<SmsShelfItem> sortBySales(@RequestBody List<SmsTemp> temps,
                                          @PathVariable(value = "shelfID") int shelfID,
                                          @PathVariable(value = "column") int column){
        temps = allocateAttr(temps);
        return sortTempsBySales(temps,column,shelfID);
    }

    @RequestMapping(value = "/sortByBBD/{shelfID}/{column}")
    public List<SmsShelfItem> sortByBBD(@RequestBody List<SmsTemp> temps,
                                        @PathVariable(value = "shelfID") int shelfID,
                                        @PathVariable(value = "column") int column){
        temps = allocateAttr(temps);
        return sortTempsByBBD(temps,column,shelfID);
    }

    public List<SmsTemp> allocateAttr(List<SmsTemp> temps) {
        for (SmsTemp temp:temps) {
//            System.out.println("传回来的没问题吧");//没问题
//            System.out.println(temp.getId());
            PmsAbstractProduct pt = abstractProductRepo.findFirstByIdEquals(temp.getId());
            temp.setUrgent(pt.getIsUrgent());
            temp.setCreated(Date.from(pt.getCreatedTime()));
            temp.setSales(pt.getSale());
        }
        return temps;
    }

    public List<SmsShelfItem> sortTempsBySales(List<SmsTemp> temps, int column,int shelfID){
        temps.sort(Comparator.comparing(SmsTemp::getSales).reversed());//卖的多的放前面，也是逆序
        return shelfSort(temps,column,shelfID);
    }

    public List<SmsShelfItem> sortTempsByBBD(List<SmsTemp> temps, int column,int shelfID){
        temps.sort(Comparator.comparing(SmsTemp::getUrgent).reversed());//1为有，故逆序
        return shelfSort(temps,column,shelfID);
    }

    public List<SmsShelfItem> sortTempsByCreated(List<SmsTemp> temps, int column,int shelfID){
        Collections.sort(temps, new Comparator<SmsTemp>() {
            @Override
            public int compare(SmsTemp temp1, SmsTemp temp2) {
                return temp2.getCreated().compareTo(temp1.getCreated());
            }
//关于这个函数可以再看看，有点高级哦
        });
        return shelfSort(temps,column,shelfID);

    }


//所以问题在这里，根据结果上看，似乎只有现在在货架上的东西才能进行这一步排序，
    public List<SmsShelfItem> shelfSort(List<SmsTemp> temps, int column,int shelfID){
        List<SmsShelfItem> res = new LinkedList<>();
//        先不难为自己，假设是最多一行不满的正常货架， 而且考虑到排设顺序很一致
//        是这样，因为在抽象货物那边掐断了，所以用abstractProductRepo查的效果并不好，不如用ShelfItems查的效果好,但是这样就要考虑一下，如果它一开始不在货架上，那就出问题了
        for (int i = column; i<column*2; i++){
            SmsShelfItem t = generateItem(shelfID,temps.get(i).getId(),2,i%column+1);
            res.add(t);
        }
        for (int i = 0; i<column; i++){
//            再把第一行放满
            SmsShelfItem t = generateItem(shelfID,temps.get(i).getId(),1,i%column+1);
            res.add(t);
        }
        for (int i = column*2; i<temps.size(); i++){
//            最后把剩下的放满
//            这里的rowNum就要自己算了
            int rowNum = (i-column*2)/column+3;
            SmsShelfItem t = generateItem(shelfID,temps.get(i).getId(),rowNum,i%column+1);
            res.add(t);
        }
        return res;
    }

    public SmsShelfItem generateItem(int shelfID, int productID, int rowNum, int colNum) {
        PmsAbstractProduct pt = abstractProductRepo.findFirstByIdEquals(productID);
        return new SmsShelfItem(
                new SmsShelfItemId(shelfID, productID),
                shelfRepo.findFirstByIdEquals(shelfID),
                pt,
                rowNum,
                colNum
        );
    }
}
