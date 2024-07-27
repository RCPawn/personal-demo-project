package com.rcpawn.controller;

import com.rcpawn.pojo.Repair;
import com.rcpawn.pojo.BorrowReturn;
import com.rcpawn.service.impl.MachineServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/machine")
public class RepairController {

    @Autowired
    MachineServiceImpl machineServiceImpl;

    //显示报修报废列表
    @GetMapping("/repair")
    public String repairList(Model model) {
        List<Repair> repairs = machineServiceImpl.getRepairList();
        model.addAttribute("repairs", repairs);
        return "repair";
    }

   //报废
   @RequestMapping("/scrapped/{deviceName}")
   public String toEditUser(@PathVariable("deviceName") String deviceName) {
       machineServiceImpl.deleteFromRepair(deviceName);
       machineServiceImpl.deleteFromMachine(deviceName);
       machineServiceImpl.addToScrapped(deviceName);
       log.info("报废设备：{}", deviceName);
       return "redirect:/machine/repair";
   }

   //显示借还列表
   @GetMapping("/borrowReturn")
   public String borrowReturn(Model model) {
       List<BorrowReturn> borrow = machineServiceImpl.getBorrowList();
       model.addAttribute("borrows", borrow);
       return "borrowReturn";
   }
}
