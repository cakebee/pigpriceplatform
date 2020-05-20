package com.nmzl.pigpriceplatform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "avg_price", schema = "PigPrice", catalog = "")
public class AvgPrice {
    private int id;
    private double price;
    private String area;
    private int type;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvgPrice avgPrice = (AvgPrice) o;
        return id == avgPrice.id &&
                Double.compare(avgPrice.price, price) == 0 &&
                type == avgPrice.type &&
                Objects.equals(area, avgPrice.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, area, type);
    }
}
