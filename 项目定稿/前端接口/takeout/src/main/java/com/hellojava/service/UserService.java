package com.hellojava.service;


import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;


public interface UserService {
//      用户登录
      QueryResponseResult findOneByUser(User user);
//      用户注册
      QueryResponseResult addUser(User user);

      //查询账户金额
      Double loadUserMoney(Integer userId);
      void updateUserMoney(Double totolPrice,Integer userId);

      //查询支付密码
      QueryResponseResult loadByPwd(User user);

      //查询当前用户信息
      QueryResponseResult findUserByUserId(Integer userId);

      //修改当前用户信息
      void editUser(User user);
}

