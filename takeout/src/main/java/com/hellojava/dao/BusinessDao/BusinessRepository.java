package com.hellojava.dao.BusinessDao;

import com.hellojava.entity.Business;
import com.hellojava.entity.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BusinessRepository extends JpaRepository<Business,Integer> {

    // 通过小分类下的所有商家
    @Query(value = "select * from business_info where bus_bustype=?1",nativeQuery = true)
    List<Business> selectBusinessTypeAll(int busTypeId);


    //查询大分类下的所有商家
    @Query(value = "SELECT * FROM business_info WHERE bus_bustype in(SELECT bus_type_id FROM business_type WHERE bus_type_pid in(SELECT bus_type_id FROM business_type WHERE bus_type_name='美食'))",nativeQuery = true)
    List<Business> selectBusinessAll(String busTypeName);

    //通过userId查找用户收藏的店铺
    @Query(value = "select * from business_info WHERE bus_id in " +
            "(SELECT business_id from collection_info where user_id=?1)",nativeQuery = true)
    List<Business> selectByUserIdCollection(int userId);


    //通过用户Id和商家Id 实现用户收藏此商家
    @Transactional
    @Modifying
    @Query(value ="insert into collection_info(user_id,business_id)values (?1,?2)",nativeQuery = true)
    int addUserIdCollection(int userId, int busId);

    //通过用户Id和商家Id 实现用户删除此商机收藏
    @Transactional
    @Modifying
    @Query(value ="delete from collection_info where user_id=?1 and business_id=?2",nativeQuery = true)
    int deleteUserIdCollection(int userId, int busId);


    @Query(value = "SELECT * FROM business_info WHERE bus_id =" +
            "(SELECT order_business FROM shopping_order WHERE order_id=?1)",nativeQuery = true)
    Business findByorderId(String orderId);
}
