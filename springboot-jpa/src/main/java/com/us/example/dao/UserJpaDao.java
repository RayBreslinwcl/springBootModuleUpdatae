package com.us.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.example.bean.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

//    @Query(value = "SELECT * FROM user WHERE address in (?1)", nativeQuery = true) //这样添加括号也可以
    @Query(value = "SELECT * FROM user WHERE address in ?1", nativeQuery = true)
    List<User> findAllByInAddress(List<String> addresses);

    /**
     * FIND_IN_SET来完成这个功能，这个函数可以用来在逗号分隔的字符串中检查某个子字符串是否存在。
     * "表名1,表名2,表名3"
     * @param address
     * @return
     */
    @Query(value = "SELECT * FROM user WHERE FIND_IN_SET(?1,address) ", nativeQuery = true)
    List<User> findAllByAddressInSet(String address);


    @Query(value = "SELECT * FROM user WHERE address =:address and name=:name", nativeQuery = true)
    @Modifying
    @Transactional
    List<User> findAllByAddressAndName(@Param("address")String address, @Param("name") String name);
}