package com.nmzl.pigpriceplatform.controller;

import com.nmzl.pigpriceplatform.entity.AvgPrice;
import com.nmzl.pigpriceplatform.entity.Price;
import com.nmzl.pigpriceplatform.pojo.AllTypePrice;
import com.nmzl.pigpriceplatform.pojo.Msg;
import com.nmzl.pigpriceplatform.service.PriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关于价格的信息，从这里可以查看原始数据或者分析结果
 * @author : zxy
 * @date : 2020/4/3 14:37
 */
@RestController
@CrossOrigin
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/avg")
    public Msg listAvgPrice() {
        List<AvgPrice> avgPriceList = priceService.listAvgPrice();
        if (avgPriceList != null) {
            return Msg.success().setData(avgPriceList);
        }
        return Msg.failed();
    }

    @GetMapping("/all/{code}")
    public Msg listAllPrice(@PathVariable(name = "code")int code) {
        List<AllTypePrice> list = priceService.listAllPrice(code);
        if (list != null) {
            return Msg.success().setData(list);
        }
        return Msg.failed();
    }

    @Deprecated
    @PostMapping("all")
    public Msg saveAllPrice() {
        List<Price> list = priceService.insertAllPrice();
        if (list != null) {
            return Msg.success();
        }
        return Msg.failed();
    }
}
