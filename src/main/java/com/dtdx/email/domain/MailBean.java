package com.dtdx.email.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailBean
 * @Description TODO
 * @Author huawei
 * @Date 2019/4/6 21:05
 * @Version 1.0
 **/
@Data
@Builder
public class MailBean implements Serializable {

    /**
     * 邮件接收者
     */
    private String recipient;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}
