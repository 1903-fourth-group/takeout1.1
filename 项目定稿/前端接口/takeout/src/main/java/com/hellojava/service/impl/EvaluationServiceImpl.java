package com.hellojava.service.impl;

import com.hellojava.dao.Evaluation.EvaluateMapper;
import com.hellojava.entity.Evaluation;
import com.hellojava.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public void saveEvaluation(Evaluation evaluation) {

        evaluateMapper.saveEvaluation(evaluation);
    }
}
