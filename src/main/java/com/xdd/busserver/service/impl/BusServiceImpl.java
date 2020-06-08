package com.xdd.busserver.service.impl;

import com.xdd.busserver.dao.BusDao;
import com.xdd.busserver.pojo.Bus;
import com.xdd.busserver.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusDao busDao;

    @Override
    public void addBus(Bus bus) {
        busDao.addBus(bus);
    }

    @Override
    public void rentBus(Bus bus) {
        busDao.rentBus(bus);
    }

    @Override
    public void returnBus(int id) {
        busDao.returnBus(id);
    }

    @Override
    public ArrayList<Bus> selectBus(int userId) {
        return busDao.selectBus(userId);
    }

    @Override
    public ArrayList<Bus> selectBusByPosition(String position) {
        return busDao.selectBusByPosition(position);
    }


}
