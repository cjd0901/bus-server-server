package com.xdd.busserver.dao;

import com.xdd.busserver.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface OrderDao {

    /**
     * 创建订单
     * @param order
     */
    @Insert("insert into `order` " +
            "(from_station,to_station,departure_time,user_id,ticket_id,price,seat,`status`,departure_date,service_price)" +
            "values " +
            "(#{fromStation},#{toStation},#{departureTime},#{userId},#{ticketId},#{price},#{seat},#{status},#{departureDate},#{servicePrice})")
    void createOrder(Order order);

    /**
     * 更新order的状态
     * @param userId
     * @param ticketId
     */
    @Update("update `order` set status = 2 where user_id = #{userId} and ticket_id = #{ticketId}")
    void updateOrder(@Param("userId") int userId, @Param("ticketId") int ticketId);

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    @Select("select * from `order` where user_id = #{userId}")
    ArrayList<Order> selectOrderByUserId(int userId);

}
