package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService
{

    @Autowired
    OrderRepository or;
    @Override
    public OrderDto createOrder(OrderDto order) {
        //dto to entity
        OrderEntity o = OrderEntity.builder().orderId(order.getOrderId()).cost(order.getCost()).items(order.getItems()).userId(order.getUserId()).status(order.isStatus()).build();
        or.save(o);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity o = or.findByOrderId(orderId);
        //entity to dto
        OrderDto odto = OrderDto.builder().id(o.getId()).orderId(o.getOrderId()).cost(o.getCost()).items(o.getItems()).userId(o.getUserId()).status(o.isStatus()).build();
        return odto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        //old order
        OrderEntity oldOrder = or.findByOrderId(orderId);
        //update the old order
        OrderEntity newOrder = OrderEntity.builder().id(oldOrder.getId()).orderId(order.getOrderId()).cost(order.getCost()).items(order.getItems()).userId(order.getUserId()).status(order.isStatus()).build();
        //save new order
        or.save(newOrder);
        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        Long x = Long.parseLong(orderId);
        or.deleteById(x);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> o = (List<OrderEntity>)or.findAll();
        List<OrderDto> ans = new ArrayList<>();
        int n = o.size();
        for(int i = 0; i<n; i++)
        {
            OrderDto odto = OrderDto.builder().id(o.get(i).getId()).orderId(o.get(i).getOrderId()).cost(o.get(i).getCost()).items(o.get(i).getItems()).userId(o.get(i).getUserId()).status(o.get(i).isStatus()).build();
            ans.add(odto);
        }
        return ans;
        //return null;
    }
}