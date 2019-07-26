package com.hellojava.service.impl;

import com.hellojava.dao.UserDao.UserMapper;
import com.hellojava.dao.UserDao.UserRepository;
import com.hellojava.entity.Business;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public QueryResponseResult findOneByUser(User user) {
        User oneByUser = userDao.findLoadByUser(user);
        int i = oneByUser != null ? 1 : 0;
        QueryResult<User> queryResult = new QueryResult<>();
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

    @Override
    public QueryResponseResult loadByPwd(User user) {
        QueryResult<User> queryResult1 = new QueryResult<>();
        User user1 = userDao.loadByPwd(user);
        int i = user1 != null ? 1 : 0;
        queryResult1.setInteger(i);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult1);
    }


    //查询当前用户信息
    @Override
    public QueryResponseResult findUserByUserId(Integer userId) {
        User userByUser = userRepository.findUserByUserId(userId);
        QueryResult<User> userQueryResult = new QueryResult<>();
        userQueryResult.setUser(userByUser);
        return new QueryResponseResult<>(CommonCode.SUCCESS,userQueryResult);
    }

    //修改信息
    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }
}
