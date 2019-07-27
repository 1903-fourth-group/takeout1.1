package com.hellojava.service.impl;

import com.github.pagehelper.PageHelper;
import com.hellojava.dao.AdminDao.AdminMapper;
import com.hellojava.entity.Admin;
import com.hellojava.entity.User;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public QueryResponseResult loadByAdmin(Admin admin) {
        QueryResult<Admin> queryResult = new QueryResult();
        Admin admin1 = adminMapper.loadByAdmin(admin);
        Integer result = admin1!=null?1:0;
        queryResult.setInteger(result);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }




}
