package com.hellojava.service.impl;

import com.github.pagehelper.PageHelper;
import com.hellojava.dao.UserDao.UserMapper;
import com.hellojava.dao.UserDao.UserRepository;
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
        User oneByUser = userDao.findOneByUser (user);
        QueryResult<User> queryResult = new QueryResult<>();
        int i = oneByUser != null ? 1 : 0;
        queryResult.setInteger (i);
        queryResult.setUser (oneByUser);
        return new QueryResponseResult(CommonCode.SUCCESS , queryResult);
    }

    @Override
    public QueryResponseResult addUser(User user) {
        QueryResult<User> queryResult1 = new QueryResult<>();
        userDao.addUser (user);
        int i = user != null ? 1 : 0;
        queryResult1.setInteger (i);
        return new QueryResponseResult(CommonCode.SUCCESS , queryResult1);
    }

    @Override
    public Double loadUserMoney(Integer userId) {
        return userDao.loadUserMoney (userId);
    }

    @Override
    public void updateUserMoney(Double totolPrice , Integer userId) {
        userDao.updateUserMoney (totolPrice , userId);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll ();
    }

    @Override
    public List<User> findUserLikely(String userName) {
        return userRepository.findByUserNameLike (userName);
    }


    @Override
    public List<User> findAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return userDao.findAll();
    }

    @Override
    public Integer getMaxCount(int rows) {
        int count=userDao.getMaxCount();
        return count%rows>0?count/rows+1:count/rows;
    }
}
