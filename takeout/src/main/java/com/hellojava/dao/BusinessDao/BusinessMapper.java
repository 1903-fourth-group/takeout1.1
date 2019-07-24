package com.hellojava.dao.BusinessDao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BusinessMapper {
    void updateBalance(@Param("busId")Integer busId,@Param("totolPrice") Double totolPrice);
}
