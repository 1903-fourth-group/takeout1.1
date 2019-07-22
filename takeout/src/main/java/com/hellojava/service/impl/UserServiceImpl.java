package com.hellojava.service.impl;

import com.hellojava.dao.UserDao.UserMapper;
import com.hellojava.entity.User;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;
    @Override
    public QueryResponseResult findOneByUser(User user) {
        User oneByUser = userDao.findOneByUser(user);
        QueryResult<User> queryResult = new QueryResult<>();
        int i = oneByUser != null ? 1 : 0;
        queryResult.setInteger(i);
        queryResult.setUser(oneByUser);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }



    @Override
    public QueryResponseResult addUser(User user) {
        QueryResult<User> queryResult1 = new QueryResult<>();
        userDao.addUser(user);
        int i = user != null ? 1 : 0;
        queryResult1.setInteger(i);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult1);
    }

    @Override
    public Double loadUserMoney(Integer userId) {
        return userDao.loadUserMoney(userId);
    }

    @Override
    public void updateUserMoney(Double totolPrice,Integer userId) {
        userDao.updateUserMoney(totolPrice,userId);
    }


}
