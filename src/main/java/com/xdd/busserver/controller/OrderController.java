package com.xdd.busserver.controller;

import com.xdd.busserver.pojo.Order;
import com.xdd.busserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/selectOrder.do")
    public ResponseEntity<List<Order>> selectOrderByUserId(int userId){
        return new ResponseEntity<>(orderService.selectOrderByUserId(userId),HttpStatus.OK);
    }
}
