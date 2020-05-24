package com.nmzl.pigpriceplatform.repository;

import com.nmzl.pigpriceplatform.entity.ExtremePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtremePriceRepository extends JpaRepository<ExtremePrice, String> {
    List<ExtremePrice> findByType(int type);

}
