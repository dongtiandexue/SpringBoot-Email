package com.dtdx.email.service;

import com.dtdx.email.domain.MailBean;

import javax.mail.MessagingException;

/**
 * @ClassName SendMailService
 * @Description TODO
 * @Author huawei
 * @Date 2019/4/6 21:08
 * @Version 1.0
 **/
public interface SendMailService {

    void sendSimpleMail(MailBean mailBean);

    void sendHtmlMail(MailBean mailBean);

    void sendAttachmentMail(MailBean mailBean);

    void sendInlineMail(MailBean mailBean);

    void sendTemplateMail(MailBean mailBean);
}
