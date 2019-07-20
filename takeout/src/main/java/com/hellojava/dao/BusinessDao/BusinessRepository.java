package com.hellojava.dao.BusinessDao;

import com.hellojava.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BusinessRepository extends JpaRepository<Business,Integer> {

    // 通过商品类型查找该类型所有商家
    @Query(value = "select * from business_info where bus_bustype in " +
            "(select bus_type_id from business_type where bus_type_pid=?1)",nativeQuery = true)
    List<Business> selectBusinessAll(int typePid);


}
