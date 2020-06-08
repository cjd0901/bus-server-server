package com.xdd.busserver.service;

import com.xdd.busserver.pojo.Order;

import java.util.ArrayList;

public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    ArrayList<Order> selectOrderByUserId(int userId);

    /**
     * 更新order的状态
     * @param userId
     * @param ticketId
     */
    void updateOrder(int userId,int ticketId);
}
