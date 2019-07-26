package com.hellojava.dao.UserDao;

import com.hellojava.entity.EmailInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2019/7/22.
 */
@Mapper
public interface EmailMapper {
    //    添加邮箱信息
    void addEmail(EmailInfo emailInfo);
    //    删除验证码
    void deleteEmailPwd(EmailInfo emailInfo);
    //    查询邮箱信息
    EmailInfo findOneEmail(EmailInfo emailInfo);

    EmailInfo findUserEmail(String userEmail);
}
