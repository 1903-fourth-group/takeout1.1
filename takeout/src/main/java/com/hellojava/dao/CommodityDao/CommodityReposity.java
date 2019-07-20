package com.hellojava.dao.CommodityDao;

import com.hellojava.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityReposity extends JpaRepository<Commodity,Integer> {

    //条件查询
    List<Commodity> findByComName(String comName);

    List<Commodity> findByComId(Integer comId);

    //多条件查询
    List<Commodity> findByComNameAndComImg(String comName, String comImg);

    //模糊查询
    List<Commodity> findByComNameLike(String comName);


}
