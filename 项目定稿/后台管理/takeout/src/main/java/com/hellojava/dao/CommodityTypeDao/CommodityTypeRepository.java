package com.hellojava.dao.CommodityTypeDao;

import com.hellojava.entity.BusinessType;
import com.hellojava.entity.CommodityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityTypeRepository extends JpaRepository<CommodityType, Integer> {

    List<BusinessType> findByComTypeName(String comTypeName);

    //通过商家查询商家商品分类
    List<CommodityType> findByBusId(Integer busId);

}
