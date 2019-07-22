package com.hellojava.dao.BusinessDao;

import com.hellojava.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommodityRepository extends JpaRepository<Commodity,Integer> {

    // 通过商家ID查找该商家所有商品
    List<Commodity> findAllBycomBus(int comBus);

    //通过商家Id查商品
    List<Commodity> findAllBycomId(int comId);

    //通过商品分类查询商品
    List<Commodity> findAllByComType(int comType);

    @Query(value = "SELECT * FROM  commodity_info WHERE com_id IN " +
            "(SELECT commodity_id FROM order_business WHERE order_id=?1)",nativeQuery = true)
    List<Commodity> findByorderId(String orderId);

}
