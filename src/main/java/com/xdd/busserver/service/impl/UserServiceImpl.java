package com.xdd.busserver.service.impl;

import com.xdd.busserver.dao.UserDao;
import com.xdd.busserver.pojo.User;
import com.xdd.busserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public int isUsernameExist(String username) {
        return userDao.isUsernameExist(username);
    }

    @Override
    public void modifyPassword(User user) {
        userDao.modifyPassword(user);
    }

    @Override
    public int checkKeyword(User user) {
        return userDao.checkKeyword(user);
    }

    @Override
    public int checkPassword(User user) {
        return userDao.checkPassword(user);
    }

    @Override
    public User selectUser(String username) {
        return userDao.selectUser(username);
    }

    @Override
    public void modifyInformation(User user) {
        userDao.modifyInformation(user);
    }

    @Override
    public void addAdmin(User user) {
        userDao.addAdmin(user);
    }


}
