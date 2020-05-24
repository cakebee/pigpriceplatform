package com.nmzl.pigpriceplatform.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "chicken_price", schema = "PigPrice", catalog = "")
public class ChickenPrice {
    private int id;
    private double price;
    private Date date;
    private String unit;
    private String rise;

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
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "rise")
    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChickenPrice that = (ChickenPrice) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(rise, that.rise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, date, unit, rise);
    }
}
