package com.hellojava.service.impl;

import com.hellojava.dao.CarouselDao.CarouselRepository;
import com.hellojava.entity.Carousel;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselRepository carouselRepository;

    @Override
    public QueryResponseResult queryCarousel() {
        List<Carousel> repositoryAll = carouselRepository.findAll();
        QueryResult<Carousel> commodityQueryResult = new QueryResult<>();
        commodityQueryResult.setList(repositoryAll);
        return new QueryResponseResult<>(CommonCode.SUCCESS,commodityQueryResult);
    }
}
