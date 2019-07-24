package com.hellojava.service;

import com.hellojava.entity.EmailInfo;
import com.hellojava.response.QueryResponseResult;

/**
 * Created by Administrator on 2019/7/22.
 */
public interface EmailInfoService {
    void addEmail(EmailInfo emailInfo);

    void deleteEmailPwd(EmailInfo emailInfo);

    QueryResponseResult findOneEmail(EmailInfo emailInfo);

    QueryResponseResult findUserEmail(String userEmail);
}
