package com.rcpawn.mapper;

import com.rcpawn.pojo.BorrowReturn;
import com.rcpawn.pojo.Machine;
import com.rcpawn.pojo.Repair;
import com.rcpawn.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MachineMapper {

    @Select("select id, machine_name, type, amount, location, factory, link, update_time from machine")
    List<Machine> getAllMachine();

    Machine queryByName(String machineName);

    void addMachine(Machine machine);

    @Delete("delete from machine where id = #{id}")
    void deleteById(Integer id);

    void update(Machine machine);

    @Select("select id, machine_name, type, amount, location, factory from machine where id = #{id}")
    Machine queryById(Integer id);

    @Select("select * from repair")
    List<Repair> getRepairList();

    //报废操作
    @Delete("delete from repair where device_name = #{deviceName}")
    void deleteFromRepair(String deviceName);

    @Delete("delete from machine where machine_name = #{deviceName}")
    void deleteFromMachine(String deviceName);

    @Insert("insert into scrapped(machine_name) values(#{deviceName})")
    void addToScrapped(String deviceName);

    @Select("select * from borrow_return")
    List<BorrowReturn> getBorrowList();

    @Insert("insert into repair(device_name) values(#{name})")
    void addToRepair(String name);

    @Delete("delete from scrapped")
    void clear();
}
