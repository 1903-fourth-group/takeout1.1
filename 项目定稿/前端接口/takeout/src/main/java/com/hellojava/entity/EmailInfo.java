package com.hellojava.entity;

import lombok.Data;

/**
 * Created by Administrator on 2019/7/20.
 */
//    邮箱信息
@Data
public class EmailInfo {
    private Integer emailId;
    private String userEmail;
    private String emailTime;
    private String emailPwd;
}
