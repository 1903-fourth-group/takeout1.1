package com.hellojava.service.impl;


import com.hellojava.dao.UserDetailDao.UserDetailRepository;
import com.hellojava.entity.UserDetail;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public void saveUserDetail(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }

    @Override
    public QueryResponseResult loadAllUserDetail(Integer userId) {
        List<UserDetail> userDetailList = userDetailRepository.findUserDetailByUserId(userId);
        QueryResult<UserDetail> userDetailQueryResult = new QueryResult<>();
        userDetailQueryResult.setList(userDetailList);
        return new QueryResponseResult<>(CommonCode.SUCCESS,userDetailQueryResult);
    }
}
