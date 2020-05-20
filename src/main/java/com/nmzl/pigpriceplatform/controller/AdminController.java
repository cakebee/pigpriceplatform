package com.nmzl.pigpriceplatform.controller;

import com.nmzl.pigpriceplatform.exception.ShellException;
import com.nmzl.pigpriceplatform.pojo.Msg;
import com.nmzl.pigpriceplatform.service.AdminService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制后台启动爬虫和数据分析
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/collect")
    public Msg doCollectData() {
        String result = adminService.startCollect();
        return Msg.success().setData(result);
    }

    @GetMapping(value = "/calculate/avg")
    public Msg doCalculate() {
        String result = adminService.startCalculate();
        return Msg.success().setData(result);
    }

    @GetMapping(value = "/test")
    public Msg doTest() {
        try {
            String result = adminService.test();
            return Msg.success().setMessage(result);
        } catch (ShellException e) {
            e.printStackTrace();
            return Msg.failed().setMessage(e.getMessage());
        }

    }
}
