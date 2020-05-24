package com.nmzl.pigpriceplatform.repository;

import com.nmzl.pigpriceplatform.entity.AllAvgPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllAvgPriceRepository extends JpaRepository<AllAvgPrice, String> {

}
