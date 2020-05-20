package com.nmzl.pigpriceplatform.repository;

import com.nmzl.pigpriceplatform.entity.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AreaCodeRepository extends JpaRepository<AreaCode, String> , Serializable {

}
