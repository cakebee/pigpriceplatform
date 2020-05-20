package com.nmzl.pigpriceplatform.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "result_table", schema = "PigPrice", catalog = "")
public class ResultTable {
    private int id;
    private String tableName;
    private Timestamp lastEditDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "last_edit_date")
    public Timestamp getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Timestamp lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultTable that = (ResultTable) o;
        return id == that.id &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(lastEditDate, that.lastEditDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableName, lastEditDate);
    }
}
