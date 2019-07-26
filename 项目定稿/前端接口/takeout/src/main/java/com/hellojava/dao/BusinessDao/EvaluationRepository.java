package com.hellojava.dao.BusinessDao;

import com.hellojava.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {

    // 通过订单编号查询评价
    @Query(value = "select * from evaluation where eva_order=?1",nativeQuery = true)
    Evaluation selectByEvaOrder(String evaOrder);
}
