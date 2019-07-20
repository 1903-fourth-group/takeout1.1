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
    public QueryResponseResult findAll() {
        List<User> all = userDao.findAll();
        QueryResult<User> userQueryResult = new QueryResult<>();
        userQueryResult.setList(all);
        return new QueryResponseResult(CommonCode.SUCCESS,userQueryResult);
    }

//    @Override
//    public User findOneByUser(User user) {
//        User oneByUser = userDao.findOneByUser(user);
//        if(oneByUser!=null){
//            return oneByUser;
//        }else {
//            return null;
//        }
//    }
    @Override
    public QueryResponseResult findOneByUser(User user) {
        User oneByUser = userDao.findOneByUser(user);
        QueryResult<User> queryResult = new QueryResult<>();
        int i = oneByUser != null ? 1 : 0;
        queryResult.setInteger(i);
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


}
