package com.hellojava.service.impl;

import com.hellojava.dao.BusinessDao.CommodityRepository;
import com.hellojava.entity.Commodity;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;


    @Override
    public QueryResponseResult loadById(int comId) {
        Optional<Commodity> byId = commodityRepository.findById(comId);
        List<Commodity> C = new ArrayList<>();
        if (byId.isPresent()){
            Commodity commodity = new Commodity();
            commodity.setComId(byId.get().getComId());
            commodity.setComName(byId.get().getComName());
            commodity.setComImg(byId.get().getComImg());
            commodity.setComPrice(byId.get().getComPrice());
            commodity.setComSalesPerMonth(byId.get().getComSalesPerMonth());
            commodity.setComBus(byId.get().getComBus());
            C.add(commodity);
        }
        QueryResult<Commodity> commodityQueryResult = new QueryResult<>();
        commodityQueryResult.setList(C);
        return new QueryResponseResult<>(CommonCode.SUCCESS,commodityQueryResult);

    }

    @Override
    public QueryResponseResult findAllBycomBus(int comBus) {
        List<Commodity> Commoditys = commodityRepository.findAllBycomBus(comBus);
        QueryResult<Commodity> commodityQueryResult = new QueryResult<>();
        commodityQueryResult.setList(Commoditys);
        return new QueryResponseResult<>(CommonCode.SUCCESS,commodityQueryResult);
    }
}
