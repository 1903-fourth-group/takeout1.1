package com.hellojava.dao.BusinessDao;

import com.hellojava.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommodityRepository extends JpaRepository<Commodity,Integer> {

    // 通过商家ID查找该商家所有商品
    List<Commodity> findAllBycomBus(int comBus);

    //通过商家Id查商品
    List<Commodity> findAllBycomId(int comId);

    //通过商品分类查询商品
    List<Commodity> findAllByComType(int comType);

}
