package com.nmzl.pigpriceplatform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "area_code", schema = "PigPrice", catalog = "")
public class AreaCode {
    private int code;
    private String name;

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaCode areaCode = (AreaCode) o;
        return code == areaCode.code &&
                Objects.equals(name, areaCode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}
