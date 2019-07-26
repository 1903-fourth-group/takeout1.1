package com.hellojava.service;

import com.hellojava.entity.Admin;
import com.hellojava.response.QueryResponseResult;

public interface AdminService {
    QueryResponseResult loadByAdmin(Admin admin);

}
