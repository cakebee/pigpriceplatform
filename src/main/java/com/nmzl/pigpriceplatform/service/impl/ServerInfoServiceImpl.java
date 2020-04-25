package com.nmzl.pigpriceplatform.service.impl;

import com.nmzl.pigpriceplatform.service.ServerInfoService;
import com.nmzl.pigpriceplatform.util.Constants;
import com.nmzl.pigpriceplatform.util.ServerInfo;
import com.nmzl.pigpriceplatform.util.ServerInfoBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ServerInfoServiceImpl implements ServerInfoService {
    @Override
    public ServerInfoBean getServerInfo() throws InterruptedException {
        ServerInfo serverInfo = new ServerInfo();
        return serverInfo.getServerInfo();
    }

    @Override
    public List<String> listServerIP() {
        return Arrays.asList(Constants.SERVER_IP_LIST);
    }
}
