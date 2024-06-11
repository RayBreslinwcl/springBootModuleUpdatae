package com.us.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.example.bean.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The Interface UserJpaDao.
 * @author abel
 */
public interface UserJpaDao extends JpaRepository<User, Long> {

    /**
     * Find by name.
     *
     * @param name the name
     * @return the user
     */
    User findByName(String name);

    @Query(value = "SELECT * FROM user WHERE address = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    List<User> findAllByAddress(String address);

}