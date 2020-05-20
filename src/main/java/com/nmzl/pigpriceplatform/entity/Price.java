package com.nmzl.pigpriceplatform.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Price {
    private int id;
    private float price;
    private Date date;
    private byte type;
    private int areaCode;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "area_code")
    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return id == price1.id &&
                Double.compare(price1.price, price) == 0 &&
                type == price1.type &&
                areaCode == price1.areaCode &&
                Objects.equals(date, price1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, date, type, areaCode);
    }
}
