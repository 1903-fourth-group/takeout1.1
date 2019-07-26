package com.hellojava.dao.CarouselDao;

import com.hellojava.entity.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarouselRepository extends JpaRepository<Carousel,Integer> {
}
