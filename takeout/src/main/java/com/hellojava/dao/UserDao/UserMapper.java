package com.hellojava.dao.UserDao;

import com.hellojava.entity.User;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //     登录
    User findLoadByUser(User user);
    //
    User findOneByUser(String userEmail);

    //     注册
    void addUser(User user);

    //    修改
    void editUser(User user);

    //查询账户钱数
    Double loadUserMoney(Integer userId);
    //支付成功减账户金额
    void updateUserMoney(@Param("totolPrice") Double totolPrice, @Param("userId") Integer userId);

    //支付密码
    User loadByPwd(User user);
}
