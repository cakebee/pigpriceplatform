package com.nmzl.pigpriceplatform.repository;

import com.nmzl.pigpriceplatform.entity.AvgPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AvgPriceRepository extends JpaRepository<AvgPrice, String>, Serializable {

}
