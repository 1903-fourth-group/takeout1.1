package com.hellojava.dao.AdminDao;


import com.hellojava.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin loadByAdmin(Admin admin);
}
