package com.rcpawn.service.impl;

import com.rcpawn.mapper.MachineMapper;
import com.rcpawn.pojo.BorrowReturn;
import com.rcpawn.pojo.Machine;
import com.rcpawn.pojo.Repair;
import com.rcpawn.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    MachineMapper machineMapper;

    @Override
    public List<Machine> getAllMachine() {
        return machineMapper.getAllMachine();
    }

    @Override
    public Machine queryByName(String machineName) {
        return machineMapper.queryByName(machineName);
    }

    @Override
    public void add(Machine machine) {
        machine.setCreateTime(LocalDateTime.now());
        machine.setUpdateTime(LocalDateTime.now());
        machineMapper.addMachine(machine);
    }

    @Override
    public void deleteById(Integer id) {
        machineMapper.deleteById(id);
    }

    @Override
    public void update(Machine machine) {
        machine.setUpdateTime(LocalDateTime.now());
        machineMapper.update(machine);
    }

    @Override
    public Machine queryById(Integer id) {
        return machineMapper.queryById(id);
    }

    @Override
    public List<Repair> getRepairList() {
        return machineMapper.getRepairList();
    }

    @Override
    public void deleteFromRepair(String deviceName) {
        machineMapper.deleteFromRepair(deviceName);
    }

    @Override
    public void deleteFromMachine(String deviceName) {
        machineMapper.deleteFromMachine(deviceName);
    }

    @Override
    public void addToScrapped(String deviceName) {
        machineMapper.addToScrapped(deviceName);

    }

    @Override
    public List<BorrowReturn> getBorrowList() {
        return machineMapper.getBorrowList();
    }

    @Override
    public void addToRepair(String name) {
        machineMapper.addToRepair(name);
    }

    @Override
    public void clear() {
        machineMapper.clear();
    }


}
