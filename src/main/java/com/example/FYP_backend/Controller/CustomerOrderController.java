package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.OmsOrderItemRepository;
import com.example.FYP_backend.DAO.OmsOrderRepository;
import com.example.FYP_backend.Model.OmsOrder;
import com.example.FYP_backend.Model.OmsOrderItem;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cp")
public class CustomerOrderController {
    @Resource
    private OmsOrderRepository orderRepo;
    @Resource
    private OmsOrderItemRepository orderItemRepo;

    @RequestMapping(value = "/ordersById/{id}")
    public List<OmsOrder> ordersById(@PathVariable int id){
        return orderRepo.findAllByMemberId(id);
    }

    @RequestMapping(value = "/orderItems/{orderID}")
    public List<OmsOrderItem> orderItems(@PathVariable int orderID){
        return orderItemRepo.findByOrder_Id(orderID);
    }

    @RequestMapping(value = "/generateOrderID")
    public int generateOrderID(){
        List<OmsOrder> orders = orderRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (orders.isEmpty()) {
            return 1;
        } else {
            return orders.get(0).getId() + 1;
        }
    }

    @RequestMapping(value = "/saveOrder")
    public void saveOrder(@RequestBody OmsOrder order){
        orderRepo.save(order);
    }

    @RequestMapping(value = "/saveOrderItems")
    public void saveOrderItems(@RequestBody List<OmsOrderItem> orderItems){
        orderItemRepo.saveAll(orderItems);
    }



}
