package com.xdd.busserver.service.impl;

import com.xdd.busserver.dao.TicketDao;
import com.xdd.busserver.pojo.Order;
import com.xdd.busserver.pojo.Ticket;
import com.xdd.busserver.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public ArrayList<Ticket> selectTicket(Ticket ticket) {
        return ticketDao.selectTicket(ticket);
    }

    @Override
    public void reduceTicket(int ticketId) {
        ticketDao.reduceTicket(ticketId);
    }

    @Override
    public void increaseTicket(int ticketId) {
        ticketDao.increaseTicket(ticketId);
    }

    @Override
    public Ticket selectTicketById(int ticketId) {
        return ticketDao.selectTicketById(ticketId);
    }

    @Override
    public void modifyTicketSku(int userId, int ticketId, int isSold) {
        ticketDao.modifyTicketSku(userId,ticketId,isSold);
    }

    @Override
    public void refundTicketSku(int userId, int ticketId, int isSold) {
        ticketDao.refundTicketSku(userId,ticketId,isSold);
    }

    @Override
    public int selectSeat(int userId, int ticketId) {
        return ticketDao.selectSeat(userId, ticketId);
    }

    @Override
    public int selectTicketCount(int userId, int ticketId) {
        return ticketDao.selectTicketCount(userId,ticketId);
    }

    @Override
    public int addTicket(Ticket ticket) {
        try {
            ticketDao.addTicketSpu(ticket);
            for (int i = 1; i <= ticket.getRestTicket() ; i++) {
                ticketDao.addTicketSku(i,ticket.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

}
