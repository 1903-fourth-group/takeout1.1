package com.hellojava.dao.BusinessTypeDao;

import com.hellojava.entity.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessTypeRepository extends JpaRepository<BusinessType,Integer> {


    // 通过商品大类型查找该类型下的小分类
    @Query(value = "select * from business_type where bus_type_pid in " +
            "(select bus_type_id from business_type where bus_type_name=?1)",nativeQuery = true)
    List<BusinessType> selectBusinessTypeAll(String busName);


}
