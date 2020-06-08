package com.xdd.busserver.service;

import com.xdd.busserver.pojo.Bus;

import java.util.ArrayList;

public interface BusService {

    void addBus(Bus bus);

    void rentBus(Bus bus);

    void returnBus(int id);

    ArrayList<Bus> selectBus(int userId);

    ArrayList<Bus> selectBusByPosition(String position);
}
