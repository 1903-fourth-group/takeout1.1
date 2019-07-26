package com.hellojava.dao.CommodityTypeDao;


import com.hellojava.entity.CommodityType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityTypeMapper {
    List<CommodityType> loadComTypeName();

}
