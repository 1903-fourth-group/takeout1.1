package com.hellojava.service;


import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;


public interface UserService {
//      查询全部
      QueryResponseResult findAll();
//      用户登录
      QueryResponseResult findOneByUser(User user);
//      用户注册
      QueryResponseResult addUser(User user);

      //查询账户金额
      Double loadUserMoney(Integer userId);
      void updateUserMoney(Double totolPrice,Integer userId);
}

