package com.xdd.busserver.controller;

import com.xdd.busserver.pojo.Bus;
import com.xdd.busserver.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/bus")
@RestController
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/addBus.do")
    public ResponseEntity<Map<String,String>> addBus(@RequestBody Bus bus){
        Map<String,String> map = new HashMap<>();
        bus.setRentState(0);
        bus.setUserId(0);
        try {
            busService.addBus(bus);
            map.put("status","2");
            map.put("message","添加成功");
            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","0");
            map.put("message","添加车辆失败");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/rentBus.do")
    public ResponseEntity<Map<String,String>> rentBus(@RequestBody Bus bus){
        Map<String,String> map = new HashMap<>();
        try {
            busService.rentBus(bus);
            map.put("status","2");
            map.put("message","租赁成功");
            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","0");
            map.put("message","租赁车辆失败");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBus.do")
    public ResponseEntity<Map<String,String>> returnBus(int busId){
        Map<String,String> map = new HashMap<>();
        try {
            busService.returnBus(busId);
            map.put("status","2");
            map.put("message","取消成功");
            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","0");
            map.put("message","退还失败");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/selectBus.do")
    public List<Bus> selectBus(int userId){
        return busService.selectBus(userId);
    }

    @PostMapping("/selectBusByPosition.do")
    public List<Bus> selectBusByPosition(String position){
        return busService.selectBusByPosition(position);
    }
}
