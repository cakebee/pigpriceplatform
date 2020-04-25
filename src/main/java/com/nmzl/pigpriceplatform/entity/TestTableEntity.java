package com.nmzl.pigpriceplatform.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * @author : zxy
 * @date : 2020/4/2 10:27
 */
@Deprecated
@Entity
@Table(name = "test_table", schema = "pigprice", catalog = "")
public class TestTableEntity {
    private int id;
    private int price;
    private Timestamp datetime;

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
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "datetime")
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTableEntity that = (TestTableEntity) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(datetime, that.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, datetime);
    }
}
