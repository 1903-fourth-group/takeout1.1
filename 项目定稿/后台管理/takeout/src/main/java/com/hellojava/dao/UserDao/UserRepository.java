package com.hellojava.dao.UserDao;


import com.hellojava.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUserId(Integer id);
    List<User> findByUserNameLike(String userName);
}
