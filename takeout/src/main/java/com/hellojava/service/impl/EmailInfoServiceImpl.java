package com.hellojava.service.impl;

import com.hellojava.dao.UserDao.EmailMapper;
import com.hellojava.dao.UserDao.UserMapper;
import com.hellojava.entity.EmailInfo;
import com.hellojava.entity.User;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.EmailInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2019/7/22.
 */
@Service
public class EmailInfoServiceImpl implements EmailInfoService {
    @Autowired
    private EmailMapper emailMapper;
    @Autowired
    private UserMapper userDao;
    @Value("${spring.mail.username}")
    private String MAIL_SENDER;
    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(Logger.class);

    private static SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void addEmail(EmailInfo emailInfo) {

        Random r = new Random();
        Integer v = r.nextInt(900000) + 100000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        emailInfo.setEmailTime(date);
        emailInfo.setEmailPwd(v.toString());
        emailMapper.addEmail(emailInfo);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(MAIL_SENDER);
            //邮件接收人
            simpleMailMessage.setTo(emailInfo.getUserEmail());
            //邮件内容
            simpleMailMessage.setText("这是您的邮箱验证码" + v);
            javaMailSender.send(simpleMailMessage);

        } catch (Exception e) {
            LOG.error("邮件发送失败", e.getMessage());
        }
    }

    //超时后删除数据库邮箱信息
    @Override
    public void deleteEmailPwd(EmailInfo emailInfo) {
        Integer s = 60;
        try {
            String emailTime = emailInfo.getEmailTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            Date time = dateFormat.parse(emailTime);
            while (true) {
                Date date = new Date();
                Long finalTime = date.getTime() - time.getTime();
                if (finalTime >= 1000 * s) {
                    emailMapper.deleteEmailPwd(emailInfo);
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //判断登录或注册是否成功
    @Override
    public QueryResponseResult findOneEmail(EmailInfo emailInfo) {
        QueryResult<EmailInfo> queryResult = new QueryResult<>();
        EmailInfo userEmail2 = emailMapper.findUserEmail(emailInfo.getUserEmail());
        Integer result = 0;
        if (userEmail2 != null) {
            if (userEmail2.getEmailPwd().equals(emailInfo.getEmailPwd())) {
                User oneByUser = userDao.findOneByUser(emailInfo.getUserEmail());
                if (oneByUser != null) {
                    EmailInfo oneEmail = emailMapper.findOneEmail(emailInfo);
                } else {
                    User user = new User();
                    user.setUserEmail(emailInfo.getUserEmail());
                    userDao.addUser(user);
                }
                result = 1;
            }
        } else {
            result = 2;
        }
        queryResult.setInteger(result);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public QueryResponseResult findUserEmail(String userEmail) {
        QueryResult<EmailInfo> queryResult = new QueryResult<>();
        EmailInfo userEmail1 = emailMapper.findUserEmail(userEmail);
        int i = userEmail1 != null ? 1 : 0;
        queryResult.setInteger(i);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}