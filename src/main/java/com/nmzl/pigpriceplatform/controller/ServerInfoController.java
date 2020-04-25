package com.nmzl.pigpriceplatform.controller;


import com.nmzl.pigpriceplatform.pojo.Msg;
import com.nmzl.pigpriceplatform.pojo.MsgFactory;
import com.nmzl.pigpriceplatform.service.ServerInfoService;
import com.nmzl.pigpriceplatform.util.ServerInfoBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(value = "/serverInfo")
public class ServerInfoController {
    @Resource
    ServerInfoService serverInfoService;

    @Resource
    MsgFactory msgFactory;

    @RequestMapping("/basicServerInfo")
    public Msg getBasicServerInfo() {
        ServerInfoBean bean;
        Msg msg;
        try {
            bean = serverInfoService.getServerInfo();
        } catch (Exception e) {
            msg = msgFactory.fail();
            return msg;
        }
        msg = msgFactory.success();
        return msg.setData(bean);
    }
}
