package com.nmzl.pigpriceplatform.service.impl;

import com.nmzl.pigpriceplatform.service.AdminService;
import com.nmzl.pigpriceplatform.util.Constants;
import com.nmzl.pigpriceplatform.util.ShellTool;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final ShellTool tool;

    public AdminServiceImpl(ShellTool tool) {
        this.tool = tool;
    }

    @Override
    public String startHDFS() {
        return null;
    }

    @Override
    public String startSpark() {
        return null;
    }

    @Override
    public String startCalculate() {
        return tool.runShell(Constants.PATH_SHELL_CALCULATE_AVG);
    }

    @Override
    public String startCollect() {
        return tool.runShell(Constants.PATH_SHELL_COLLECT);
    }

    @Override
    public String test() {
        return tool.runShell(Constants.PATH_SHELL_TEST);
    }

}
