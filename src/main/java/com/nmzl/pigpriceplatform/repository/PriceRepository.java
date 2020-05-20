package com.nmzl.pigpriceplatform.repository;

import com.nmzl.pigpriceplatform.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, String> , Serializable {
    List<Price> getByAreaCode(int code);
}
