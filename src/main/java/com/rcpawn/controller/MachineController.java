package com.rcpawn.controller;

import com.rcpawn.pojo.Machine;
import com.rcpawn.service.impl.MachineServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    MachineServiceImpl machineServiceImpl;

    //借取归还
    @RequestMapping("/borrowReturn")
    public String borrowReturn() {
        return "/borrowReturn";
    }

    //显示设备列表
    @GetMapping("/machineList")
    public String getMachine(Model model) {
        List<Machine> machineList = machineServiceImpl.getAllMachine();
        log.info("查询所有设备信息：{}", machineList);
        model.addAttribute("machines", machineList);
        return "machineList";
    }

    //添加设备
    @RequestMapping("/toAdd")
    public String toAddMachine() {
        return "/addMachine";
    }
    @RequestMapping("/add")
    public String addMachine(Model model, @Validated Machine machine, MultipartFile image, @RequestParam("fileName") String fileName) throws IOException {
        String name = machine.getMachineName();
        Machine machine1 = machineServiceImpl.queryByName(name);
        log.info("文件上传：{}", fileName);

        if (machine1 != null) {
            model.addAttribute("error", "设备名已存在！");
            return "addMachine";
        }

        if (!image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String extname = originalFilename.substring(index);
            String newFileName = UUID.randomUUID() + extname;
            log.info("获取到的新文件名：{}", newFileName);
            image.transferTo(new File("E:\\upload\\image\\" + newFileName));
            log.info("文件上传完成");
        }

        machineServiceImpl.add(machine);
        machineServiceImpl.addToRepair(name);
        log.info("添加成功：{},{}", machine, name);
        model.addAttribute("success", "添加成功！");
        return "redirect:/machine/machineList";
    }

    //删除
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id) {
        machineServiceImpl.deleteById(id);
        log.info("删除设备：{}", id);
        return "redirect:/machine/machineList";
    }

    //搜索
    @RequestMapping("/search")
    public String queryUser(Model model, Machine machines) {
        Machine machine = machineServiceImpl.queryByName(machines.getMachineName());
        model.addAttribute("machines", machine);
        log.info("通过设备名查询：{}", machine);
        return "machineList";
    }

    //编辑设备
    @RequestMapping("/toEdit/{id}")
    public String toEditUser(@PathVariable("id") Integer id, Model model) {
        Machine machine = machineServiceImpl.queryById(id);
        model.addAttribute("machine", machine);
        return "editMachine";
    }
    @RequestMapping("/edit")
    public String editUser(@ModelAttribute("machine") Machine machine, Model model) {
        Machine existingMachine = machineServiceImpl.queryByName(machine.getMachineName());
        if (existingMachine != null) {
            machineServiceImpl.update(machine);
            log.info("编辑成功：{}", machine);
            model.addAttribute("success", "编辑成功！");
            return "redirect:/machine/machineList";
        } else {
            model.addAttribute("error", "不能修改设备名");
        }
        return "editMachine";
    }

    //清除报废设备
    @RequestMapping("/clear")
    public String clear() {
        machineServiceImpl.clear();
        log.info("清空报废表!");
        return "redirect:/machine/machineList";
    }

}
