package com.hellojava.service;

import com.hellojava.entity.Admin;
import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;

import java.util.List;

public interface AdminService {
    QueryResponseResult loadByAdmin(Admin admin);


}
