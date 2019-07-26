package com.hellojava.response;

import com.hellojava.entity.Business;
import com.hellojava.entity.EmailInfo;
import com.hellojava.entity.Order;
import com.hellojava.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
    //判断
    private Integer integer;

    private User user;

    private Order order;

    private Business business;

    private EmailInfo emailInfo;

}
