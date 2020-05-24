package com.nmzl.pigpriceplatform.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ExtremePriceChart {
    private Date date;

    private float price;

    private String area;
}
