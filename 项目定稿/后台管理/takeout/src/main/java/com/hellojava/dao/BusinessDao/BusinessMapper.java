package com.hellojava.dao.BusinessDao;


import com.hellojava.entity.Business;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessMapper {
    List<Business> loadBusName();

    Integer getMaxCount();

}
