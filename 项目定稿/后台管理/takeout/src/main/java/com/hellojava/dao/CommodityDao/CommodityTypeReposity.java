package com.hellojava.dao.CommodityDao;

import com.hellojava.entity.CommodityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityTypeReposity extends JpaRepository<CommodityType,Integer> {

    //通过商家Id查该商家对应的商品分类
    List<CommodityType> findAllBybusId(int busId);
    void deleteCommodityTypesByBusId(Integer busId);


}
