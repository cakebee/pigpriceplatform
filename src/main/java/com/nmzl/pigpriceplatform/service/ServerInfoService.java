package com.nmzl.pigpriceplatform.service;

import com.nmzl.pigpriceplatform.util.ServerInfoBean;
import org.apache.shiro.crypto.hash.Hash;

import java.util.HashMap;
import java.util.List;

public interface ServerInfoService {

    /**
     * 获取服务器硬件信息、JVM信息
     * @return :
     * @author : zxy
     * @date : 2020/4/22 8:58 下午
     */
    ServerInfoBean getServerInfo() throws InterruptedException;

    /**
     * TODO
     * 获取所有服务器的IP地址，开发阶段使用常量，后期需要改成在数据库中存储服务器的IP地址
     * @return :
     * @author : zxy
     * @date : 2020/4/22 9:00 下午
     */
    List<String> listServerIP();
}
