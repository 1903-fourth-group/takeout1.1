package com.hellojava.dao.UserDao;

import com.hellojava.entity.User;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //     查询全部
    List<User> findAll();
    //     登录
    User findOneByUser(User user);
    //     注册
    void addUser(User user);

    //查询账户钱数
    Double loadUserMoney(Integer userId);
    //支付成功减账户金额
    void updateUserMoney(@Param("totolPrice") Double totolPrice, @Param("userId") Integer userId);
}
