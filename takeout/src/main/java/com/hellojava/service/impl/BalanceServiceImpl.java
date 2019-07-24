package com.hellojava.service.impl;

import com.hellojava.dao.BusinessDao.BusinessMapper;
import com.hellojava.entity.Business;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private BusinessMapper businessMapper;


    @Override
    public void updateBalance(Integer busId,Double totolPrice) {
        QueryResult<Business> queryResult = new QueryResult();
        businessMapper.updateBalance(busId, totolPrice);
    }
}
