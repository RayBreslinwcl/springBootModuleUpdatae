package cn.abel.dao;

import java.util.List;
import java.util.Map;

import cn.abel.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository //Dao层
@Mapper //注释表示是一个mybatis的mappler类
public interface UserDao {

    List<User> getByMap(Map<String, Object> map);

    User getById(Integer id);

    Integer create(User user);

    int update(User user);

    int delete(Integer id);

    List<User> getUsers();
}