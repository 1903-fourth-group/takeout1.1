package com.hellojava.service;


import com.hellojava.entity.UserDetail;
import com.hellojava.response.QueryResponseResult;

public interface UserDetailService {
    void saveUserDetail(UserDetail userDetail);
    QueryResponseResult loadAllUserDetail(Integer userId);
}
