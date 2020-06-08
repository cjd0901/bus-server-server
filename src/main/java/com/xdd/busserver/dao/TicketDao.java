package com.xdd.busserver.dao;

import com.xdd.busserver.pojo.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface TicketDao {

    /**
     * 根据车站和日期查询车票
     * @param ticket
     * @return
     */
    @Select("select * from ticket where " +
            "from_station = #{fromStation} and to_station = #{toStation} and departure_date = #{departureDate} order by departure_time")
    ArrayList<Ticket> selectTicket(Ticket ticket);

    /**
     * 减少对应车票
     * @param ticketId
     */
    @Update("update ticket set rest_ticket = rest_ticket - 1 where id = #{ticketId}")
    void reduceTicket(int ticketId);

    /**
     * 增加对应车票
     * @param ticketId
     */
    @Update("update ticket set rest_ticket = rest_ticket + 1 where id = #{ticketId}")
    void increaseTicket(int ticketId);

    /**
     * 根据id查询车票
     * @param ticketId
     * @return
     */
    @Select("select * from ticket where id = #{ticketId}")
    Ticket selectTicketById(int ticketId);

    /**
     * 更新ticketSku的状态
     * @param userId
     * @param ticketId
     */
    @Update("update ticket_sku set is_sold = #{isSold}, user_id = #{userId} where id =\n" +
            "(\n" +
            "\tselect t.id from (select * from ticket_sku where ticket_id = #{ticketId} and is_sold = 0 LIMIT 1) as t\n" +
            ")")
    void modifyTicketSku(@Param("userId") int userId, @Param("ticketId") int ticketId, @Param("isSold") int isSold);

    /**
     * 退票时修改sku状态
     * @param userId
     * @param ticketId
     * @param isSold
     */
    @Update("update ticket_sku set is_sold = #{isSold}, user_id = #{userId} where id =\n" +
            "(\n" +
            "\tselect t.id from (select * from ticket_sku where ticket_id = #{ticketId} and is_sold = 1 LIMIT 1) as t\n" +
            ")")
    void refundTicketSku(@Param("userId") int userId, @Param("ticketId") int ticketId, @Param("isSold") int isSold);

    /**
     * 查询座位号
     * @param userId
     * @param ticketId
     * @return
     */
    @Select("select seat from ticket_sku where user_id = #{userId} and ticket_id = #{ticketId}")
    int selectSeat(@Param("userId") int userId,@Param("ticketId") int ticketId);

    /**
     * 查询用户是否已经购买了该车票
     * @param userId
     * @param ticketId
     * @return
     */
    @Select("select count(1) from ticket_sku where user_id = #{userId} and ticket_id = #{ticketId}")
    int selectTicketCount(@Param("userId") int userId,@Param("ticketId") int ticketId);
    /**
     * 添加车票spu
     * @param ticket
     */
    @Insert("insert into ticket " +
            "(from_station,to_station,departure_date,price,rest_ticket,service_price,departure_time,bus_spec) " +
            "values " +
            "(#{fromStation},#{toStation},#{departureDate},#{price},#{restTicket},#{servicePrice},#{departureTime},#{busSpec})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addTicketSpu(Ticket ticket);

    /**
     * 添加车票sku
     * @param seat
     * @param ticketId
     */
    @Insert("insert into ticket_sku (seat,ticket_id) values (#{seat},#{ticketId})")
    void addTicketSku(@Param("seat") int seat,@Param("ticketId") int ticketId);
}
