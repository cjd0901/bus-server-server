package com.xdd.busserver.controller;

import com.xdd.busserver.pojo.User;
import com.xdd.busserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserConfroller {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/addUser.do")
    public ResponseEntity<Map<String,String>> addUser(@RequestBody User user){
        Map<String,String> map = new HashMap<>();
        user.setRoleId(1);
        user.setGender(1);
        if(userService.isUsernameExist(user.getUsername())<1 && user.getUsername() != ""){
            try {
                userService.addUser(user);
            }catch (Exception e){
                e.printStackTrace();
                map.put("status","0");
                map.put("message","注册失败");
                return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
            }
        }else if (user.getUsername() == ""){
            map.put("status","3");
            map.put("message","用户名不能为空");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }else {
            map.put("status","1");
            map.put("message","用户名已存在");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        map.put("status","2");
        map.put("message","注册成功");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @PostMapping("/modifyPassword.do")
    public ResponseEntity<Map<String,String>> modifyPassword(@RequestBody User user){
        Map<String,String> map = new HashMap<>();
        if(userService.checkKeyword(user) != 1){
            map.put("status","1");
            map.put("message","二级密码不正确");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        try {
            userService.modifyPassword(user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","0");
            map.put("message","修改密码失败");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        map.put("status","2");
        map.put("message","修改密码成功");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login.do")
    public ResponseEntity<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        if(userService.checkPassword(user) < 1){
            map.put("status","1");
            map.put("message","用户名或密码不正确");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        User u = userService.selectUser(user.getUsername());
        map.put("status","2");
        map.put("message","登录成功");
        map.put("user",u);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    /**
     * 修改信息
     * @param user
     * @return
     */
    @PostMapping("/modifyInformation.do")
    public ResponseEntity<Map<String,Object>> modifyInformation(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        try {
            userService.modifyInformation(user);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status","0");
            map.put("message","修改失败");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        map.put("status","2");
        map.put("message","修改成功");
        map.put("user",userService.selectUser(user.getUsername()));
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    /**
     * 添加管理员
     * @param user
     */
    @PostMapping("/addAdmin.do")
    public ResponseEntity<Map<String,String>> addAdmin(@RequestBody User user){
        Map<String,String> map = new HashMap<>();
        try {
            user.setRoleId(0);
            userService.addAdmin(user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","0");
            map.put("message","添加管理员失败");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        map.put("status","2");
        map.put("message","添加成功");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
