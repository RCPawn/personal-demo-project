package com.rcpawn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/screen")
public class ScreenController {

    @RequestMapping
    public String showScreen() {
        return "screen";
    }
}
