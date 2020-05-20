package com.nmzl.pigpriceplatform.controller;


import com.nmzl.pigpriceplatform.pojo.Msg;
import com.nmzl.pigpriceplatform.service.ServerInfoService;
import com.nmzl.pigpriceplatform.util.ServerInfoBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取服务器硬件、软件信息
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(value = "/serverInfo")
public class ServerInfoController {

    private final ServerInfoService serverInfoService;

    public ServerInfoController(ServerInfoService serverInfoService) {
        this.serverInfoService = serverInfoService;
    }

    @RequestMapping("/basicServerInfo")
    public Msg getBasicServerInfo() {
        ServerInfoBean bean;
        try {
            bean = serverInfoService.getServerInfo();
        } catch (Exception e) {
            return Msg.failed().setMessage(e.getMessage());
        }
        return Msg.success().setData(bean);
    }
}
