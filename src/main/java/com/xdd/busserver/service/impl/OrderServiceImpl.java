package com.xdd.busserver.service.impl;

import com.xdd.busserver.dao.OrderDao;
import com.xdd.busserver.pojo.Order;
import com.xdd.busserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void createOrder(Order order) {
        orderDao.createOrder(order);
    }

    @Override
    public ArrayList<Order> selectOrderByUserId(int userId) {
        return orderDao.selectOrderByUserId(userId);
    }

    @Override
    public void updateOrder(int userId, int ticketId) {
        orderDao.updateOrder(userId,ticketId);
    }
}
