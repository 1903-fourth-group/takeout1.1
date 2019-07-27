package com.hellojava.service;


import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;

import java.util.List;


public interface UserService {
//      用户登录
      QueryResponseResult findOneByUser(User user);
//      用户注册
      QueryResponseResult addUser(User user);

      //查询账户金额
      Double loadUserMoney(Integer userId);
      void updateUserMoney(Double totolPrice, Integer userId);

      //查询所有用户
      List<User> findAllUser();

      //模糊查询
      List<User> findUserLikely(String userName);

      List<User> findAll(int page,int rows);

      Integer getMaxCount(int rows);
}

