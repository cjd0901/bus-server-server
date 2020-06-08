package com.xdd.busserver.service;

import com.xdd.busserver.pojo.Order;
import com.xdd.busserver.pojo.Ticket;

import java.util.ArrayList;


public interface TicketService {

    /**
     * 根据车站和日期查询车票
     * @param ticket
     * @return
     */
    ArrayList<Ticket> selectTicket(Ticket ticket);

    /**
     * 减少对应的车票
     * @param ticketId
     */
    void reduceTicket(int ticketId);

    /**
     * 增加对应的车票
     * @param ticketId
     */
    void increaseTicket(int ticketId);

    /**
     * 根据id查询车票
     * @param ticketId
     * @return
     */
    Ticket selectTicketById(int ticketId);

    /**
     * 更新sku状态
     * @param userId
     * @param ticketId
     */
    void modifyTicketSku(int userId,int ticketId,int isSold);

    /**
     * 退票是修改sku状态
     * @param userId
     * @param ticketId
     * @param isSold
     */
    void refundTicketSku(int userId,int ticketId,int isSold);

    /**
     * 查询座位号
     * @param userId
     * @param ticketId
     * @return
     */
    int selectSeat(int userId,int ticketId);

    /**
     * 查询用户是否已经购买了该车票
     * @param userId
     * @param ticketId
     * @return
     */
    int selectTicketCount(int userId,int ticketId);

    /**
     * 添加车票
     * @param ticket
     * @return
     */
    int addTicket(Ticket ticket);

}
