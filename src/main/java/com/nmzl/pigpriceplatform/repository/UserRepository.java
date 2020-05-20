package com.nmzl.pigpriceplatform.repository;

import com.nmzl.pigpriceplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zxy
 * @date : 2020/4/3 20:18
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, Serializable {
    User getByToken(String token);

    User findByUsername(String userName);
}
