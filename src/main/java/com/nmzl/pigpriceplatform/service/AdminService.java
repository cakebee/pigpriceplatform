package com.nmzl.pigpriceplatform.service;

public interface AdminService {
    String startHDFS();

    String startSpark();

    String startCalculate();

    String startCollect();

    String test();
}
