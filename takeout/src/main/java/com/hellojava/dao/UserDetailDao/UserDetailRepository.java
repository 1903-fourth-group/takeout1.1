package com.hellojava.dao.UserDetailDao;

import com.hellojava.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    List<UserDetail> findUserDetailByUserId(Integer userId);
}
