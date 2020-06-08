package com.xdd.busserver.dao;

import com.xdd.busserver.pojo.Advice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface AdviceDao {

    @Insert("insert into advice (content) values (#{content})")
    void addAdvice(Advice advice);

    @Select("select * from advice order by create_time desc")
    ArrayList<Advice> selectAdvice();
}
