package com.nmzl.pigpriceplatform.service;

import com.nmzl.pigpriceplatform.entity.AvgPrice;
import com.nmzl.pigpriceplatform.entity.Price;
import com.nmzl.pigpriceplatform.pojo.AllTypePrice;

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
}
