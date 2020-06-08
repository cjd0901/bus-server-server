package com.xdd.busserver.controller;

import com.xdd.busserver.pojo.Order;
import com.xdd.busserver.pojo.Ticket;
import com.xdd.busserver.service.OrderService;
import com.xdd.busserver.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/ticket")
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/selectTicket.do")
    public ResponseEntity<Object> selectTicket(@RequestBody Ticket ticket){
        List<Ticket> ticketList = ticketService.selectTicket(ticket);
        return new ResponseEntity<>(ticketList,HttpStatus.OK);
    }

    @PostMapping("/buyTicket.do")
    public ResponseEntity<Map<String,Object>> buyTicket(@RequestBody Order order){
        Map<String,Object> map = new HashMap<>();
        if(ticketService.selectTicketById(order.getTicketId()).getRestTicket() == 0){
            map.put("status","1");
            map.put("message","该车票已经售完");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }else {
            if(ticketService.selectTicketCount(order.getUserId(),order.getTicketId())<1){
                ticketService.reduceTicket(order.getTicketId());
                ticketService.modifyTicketSku(order.getUserId(),order.getTicketId(),1);
                int seat = ticketService.selectSeat(order.getUserId(),order.getTicketId());
                order.setSeat(seat);
                order.setStatus(1);
                try {
                    orderService.createOrder(order);
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("status","0");
                    map.put("message","购买车票失败");
                    return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
                }
            }else {
                map.put("status","1");
                map.put("message","您已购买了该车票");
                return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
            }
        }
        map.put("status","2");
        map.put("message","购买车票成功");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PostMapping("/refund.do")
    public ResponseEntity<Map<String,String>> refund(@RequestBody Order order){
        Map<String,String> map = new HashMap<>();
        try {
            ticketService.increaseTicket(order.getTicketId());
            ticketService.refundTicketSku(0,order.getTicketId(),0);
            orderService.updateOrder(order.getUserId(),order.getTicketId());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","0");
            map.put("message","退票失败，请稍后再试");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        map.put("status","2");
        map.put("message","退票成功");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PostMapping("/addTicket.do")
    public ResponseEntity<Map<String,String>> addTicket(@RequestBody Ticket ticket){
        int x = ticketService.addTicket(ticket);
        Map<String,String> map = new HashMap<>();
        if(x == 0){
            map.put("status","2");
            map.put("message","添加车票成功");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }else{
            map.put("status","0");
            map.put("message","添加车票失败");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }

    }
}
