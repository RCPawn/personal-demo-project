package com.rcpawn.service;

import com.rcpawn.pojo.BorrowReturn;
import com.rcpawn.pojo.Machine;
import com.rcpawn.pojo.Repair;

import java.util.List;

public interface MachineService {

    List<Machine> getAllMachine();

    Machine queryByName(String machineName);

    void add(Machine machine);

    void deleteById(Integer id);

    void update(Machine machine);

    Machine queryById(Integer id);

    List<Repair> getRepairList();

    void deleteFromRepair(String deviceName);

    void deleteFromMachine(String deviceName);

    void addToScrapped(String deviceName);

    List<BorrowReturn> getBorrowList();

    void addToRepair(String name);

    void clear();
}
