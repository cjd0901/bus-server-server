package com.xdd.busserver.service.impl;

import com.xdd.busserver.dao.AdviceDao;
import com.xdd.busserver.pojo.Advice;
import com.xdd.busserver.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceDao adviceDao;

    @Override
    public void addAdvice(Advice advice) {
        adviceDao.addAdvice(advice);
    }

    @Override
    public ArrayList<Advice> selectAdvice() {
        return adviceDao.selectAdvice();
    }
}
