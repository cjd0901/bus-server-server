package com.xdd.busserver.dao;

import com.xdd.busserver.pojo.Bus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface BusDao {

    @Insert("insert into bus " +
            "(bus_spec,price,position,license_plate,rent_state,user_id) " +
            "values " +
            "(#{busSpec},#{price},#{position},#{licensePlate},0,#{userId})")
    void addBus(Bus bus);

    @Update("update bus set user_id = #{userId}, rent_state = 1 where id = #{id}")
    void rentBus(Bus bus);

    @Update("update bus set user_id = 0, rent_state = 0 where id = #{id}")
    void returnBus(int id);

    @Select("select * from bus where user_id = #{userId}")
    ArrayList<Bus> selectBus(int userId);

    @Select("select * from bus where position = #{position} and rent_state = 0")
    ArrayList<Bus> selectBusByPosition(String position);
}
