package com.nmzl.pigpriceplatform.repository;

import com.nmzl.pigpriceplatform.entity.ChickenPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChickenPriceRepository extends JpaRepository<ChickenPrice, String> {
    List<ChickenPrice> findAllByDateAfterAndDateBefore(Date dateAfter, Date dateBefore);
}
