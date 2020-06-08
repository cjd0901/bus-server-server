package com.xdd.busserver.controller;

import com.xdd.busserver.pojo.Advice;
import com.xdd.busserver.service.AdviceService;
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

@RequestMapping("/advice")
@RestController
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @PostMapping("/addAdvice.do")
    public ResponseEntity<Map<String,String>> addService(@RequestBody Advice advice){
        Map<String,String> map = new HashMap<>();
        try {
            adviceService.addAdvice(advice);
            map.put("status","2");
            map.put("message","感谢您的建议");
            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","0");
            map.put("message","提交失败，请稍后再试");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/selectAdvice.do")
    public ResponseEntity<List<Advice>> selectAdvice(){
        return new ResponseEntity<>(adviceService.selectAdvice(),HttpStatus.OK);
    }
}
