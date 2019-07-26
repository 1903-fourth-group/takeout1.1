package com.hellojava.dao.Evaluation;

import com.hellojava.entity.Evaluation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluateMapper{

    void saveEvaluation(Evaluation evaluation);
}
