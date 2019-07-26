package com.hellojava.service.impl;

import com.hellojava.dao.BusinessDao.BusinessRepository;
import com.hellojava.dao.BusinessDao.EvaluationRepository;
import com.hellojava.dao.BusinessTypeDao.BusinessTypeRepository;
import com.hellojava.dao.OrderDao.OrderRepository;
import com.hellojava.entity.Business;
import com.hellojava.entity.BusinessType;
import com.hellojava.entity.Evaluation;
import com.hellojava.entity.Order;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    // 通过商品大类型查找该类型下的小分类
    @Override
    public QueryResponseResult selectBusinessTypeAll(String busTypeName){
        List<BusinessType> businesses = businessTypeRepository.selectBusinessTypeAll(busTypeName);
        QueryResult<BusinessType> businessQueryResult = new QueryResult<>();
        businessQueryResult.setList(businesses);
        return new QueryResponseResult<>(CommonCode.SUCCESS,businessQueryResult);
    }

    // 查询大分类下的所有商家
    @Override
    public QueryResponseResult selectBusinessAll(String busTypeName){
        List<Business> businesses = businessRepository.selectBusinessAll(busTypeName);
        QueryResult<Business> businessQueryResult = new QueryResult<>();
        businessQueryResult.setList(businesses);
        return new QueryResponseResult<>(CommonCode.SUCCESS,businessQueryResult);
    }

    @Override
    public QueryResponseResult selectBusinessAll(int busType) {
        List<Business> businesses = businessRepository.selectBusinessTypeAll(busType);
        QueryResult<Business> businessQueryResult = new QueryResult<>();
        businessQueryResult.setList(businesses);
        return new QueryResponseResult<>(CommonCode.SUCCESS,businessQueryResult);
    }

    @Override
    public QueryResponseResult loadBybusId(int busId){
        Optional<Business> byId = businessRepository.findById(busId);
        List<Business> businesses = new ArrayList<>();
        if (byId.isPresent()){
            Business b = new Business();
            b.setBusId(byId.get().getBusId());
            b.setBusName(byId.get().getBusName());
            b.setBusImg(byId.get().getBusImg());
            b.setBusAddress(byId.get().getBusAddress());
            b.setBusSalesPerMonth(byId.get().getBusSalesPerMonth());
            b.setMinDelivery(byId.get().getMinDelivery());
            b.setDeliveryCostDay(byId.get().getDeliveryCostDay());
            b.setDeliveryCostNight(byId.get().getDeliveryCostNight());
            b.setConsumePerHead(byId.get().getConsumePerHead());
            b.setDebit(byId.get().getDebit());
            b.setDistance(byId.get().getDistance());
            b.setDeliveryTime(byId.get().getDeliveryTime());
            b.setBussType(byId.get().getBussType());
            b.setBusBalance(byId.get().getBusBalance());
            b.setBusGrade(byId.get().getBusGrade());
            b.setBusDiscount(byId.get().getBusDiscount());
            b.setBusNotice(byId.get().getBusNotice());
            b.setInsurance(byId.get().getInsurance());
            businesses.add(b);
        }
        QueryResult<Business> businessQueryResult = new QueryResult<>();
        businessQueryResult.setList(businesses);
        return new QueryResponseResult<>(CommonCode.SUCCESS,businessQueryResult);
    }

    @Override
    public QueryResponseResult selectByBusIdEvaluation(int busId) {
        List<Order> orders = orderRepository.selectBybusId(busId);
        List<String> i = new ArrayList<>();
        for (Order o:orders) {
            i.add(o.getOrderId());
        }
        List<Evaluation> Es = new ArrayList<>();
        for (int y = 0; y < i.size(); y++) {
            Evaluation evaluation = evaluationRepository.selectByEvaOrder(i.get(y));
            if (evaluation!=null){
                Es.add(evaluation);
            }
        }
        QueryResult<Evaluation> evaluationQueryResult = new QueryResult<>();
        evaluationQueryResult.setList(Es);
        return new QueryResponseResult<>(CommonCode.SUCCESS,evaluationQueryResult);
    }



    @Override
    public QueryResponseResult selectByUserIdCollection(int userId) {
        List<Business> businesses = businessRepository.selectByUserIdCollection(userId);
        QueryResult<Business> BusinessQueryResult = new QueryResult<>();
        BusinessQueryResult.setList(businesses);
        return new QueryResponseResult<>(CommonCode.SUCCESS,BusinessQueryResult);
    }


    @Override
    public int addUserIdCollection(int userId, int busId) {
        return businessRepository.addUserIdCollection(userId, busId);
    }

    @Override
    public int delectUserIdCollection(int userId, int busId) {
        return businessRepository.deleteUserIdCollection(userId, busId);
    }
}
