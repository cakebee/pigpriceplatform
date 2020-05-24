package com.nmzl.pigpriceplatform.service;

import com.nmzl.pigpriceplatform.entity.*;
import com.nmzl.pigpriceplatform.pojo.AllTypePrice;
import com.nmzl.pigpriceplatform.pojo.ExtremePriceChart;
import com.nmzl.pigpriceplatform.pojo.PigAndChicken;

import java.util.HashMap;
import java.util.List;

/**
 * 处理猪肉价格相关业务，价格包括原始价格以及计算后的最高价等
 * @author : zxy
 * @date : 2020/4/3 14:29
 */
public interface PriceService {
    List<AvgPrice> listAvgPrice();

    List<AllTypePrice> listAllPrice();

    List<Price> insertAllPrice();

    List<AllTypePrice> listAllPrice(int code);

    HashMap listExtremePrice(int type);

    List<ChickenPrice> listChickenPrice();

    List<AllAvgPrice> listAllAvgPrice();

    List<PigAndChicken> listPigAndChicken();

    List<Result> getPigAndChickenCovariance();
}
