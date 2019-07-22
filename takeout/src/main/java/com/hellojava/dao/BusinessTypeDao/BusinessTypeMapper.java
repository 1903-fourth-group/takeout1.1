package com.hellojava.dao.BusinessTypeDao;

import com.hellojava.entity.BusinessType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessTypeMapper {
    //在商家信息中显示商家分类的名称
    List<BusinessType> loadBusTypeName();

    //在商家分类表中查询前5条数据，五大类
    List<BusinessType> loadBusTypeNameTop5();

    //查询二级分类的信息
    List<BusinessType> loadBusTypeNameSecond(Integer typeId);

}
