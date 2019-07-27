package com.hellojava.dao.AdminDao;


import com.hellojava.entity.Admin;
import com.hellojava.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    Admin loadByAdmin(Admin admin);

}
