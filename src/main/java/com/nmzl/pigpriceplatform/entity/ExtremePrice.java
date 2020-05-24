package com.nmzl.pigpriceplatform.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "extreme_price", schema = "PigPrice", catalog = "")
public class ExtremePrice {
    private int id;
    private String area;
    private int type;
    private float maxPrice;
    private Date maxPriceDate;
    private float minPrice;
    private Date minPriceDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "max_price")
    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Basic
    @Column(name = "max_price_date")
    public Date getMaxPriceDate() {
        return maxPriceDate;
    }

    public void setMaxPriceDate(Date maxPriceDate) {
        this.maxPriceDate = maxPriceDate;
    }

    @Basic
    @Column(name = "min_price")
    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    @Basic
    @Column(name = "min_price_date")
    public Date getMinPriceDate() {
        return minPriceDate;
    }

    public void setMinPriceDate(Date minPriceDate) {
        this.minPriceDate = minPriceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtremePrice that = (ExtremePrice) o;
        return id == that.id &&
                type == that.type &&
                Float.compare(that.maxPrice, maxPrice) == 0 &&
                Float.compare(that.minPrice, minPrice) == 0 &&
                Objects.equals(area, that.area) &&
                Objects.equals(maxPriceDate, that.maxPriceDate) &&
                Objects.equals(minPriceDate, that.minPriceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area, type, maxPrice, maxPriceDate, minPrice, minPriceDate);
    }
}
