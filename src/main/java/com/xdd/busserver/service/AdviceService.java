package com.xdd.busserver.service;

import com.xdd.busserver.pojo.Advice;

import java.util.ArrayList;

public interface AdviceService {

    void addAdvice(Advice advice);

    ArrayList<Advice> selectAdvice();
}
