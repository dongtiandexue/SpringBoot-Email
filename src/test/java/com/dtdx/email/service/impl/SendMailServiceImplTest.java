package com.dtdx.email.service.impl;

import com.dtdx.email.EmailApplication;
import com.dtdx.email.domain.MailBean;
import com.dtdx.email.service.SendMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EmailApplication.class})// 指定启动类
public class SendMailServiceImplTest {

    @Autowired
    private SendMailService sendMailService;

    @Test
    public void sendSimpleMail() {

        MailBean mailBean = MailBean.builder().recipient("1542017576@qq.com").subject("springboot 邮件测试").content("这是一封测试邮件").build();
        sendMailService.sendSimpleMail(mailBean);

    }

    @Test
    public void sendHtmlMail() {
        MailBean mailBean = MailBean.builder().recipient("1542017576@qq.com").subject("springboot 邮件测试").content("这是一封测试邮件").build();
        sendMailService.sendHtmlMail(mailBean);

    }

    @Test
    public void sendAttachmentMail() {
        MailBean mailBean = MailBean.builder().recipient("1542017576@qq.com").subject("springboot 邮件测试").content("这是一封测试邮件").build();
        sendMailService.sendAttachmentMail(mailBean);
    }

    @Test
    public void sendInlineMail() {
        MailBean mailBean = MailBean.builder().recipient("1542017576@qq.com").subject("springboot 邮件测试").content("这是一封测试邮件").build();
        sendMailService.sendAttachmentMail(mailBean);
    }

    @Test
    public void sendTemplateMail() {
        MailBean mailBean = MailBean.builder().recipient("1542017576@qq.com").subject("springboot 邮件测试").content("这是一封测试邮件").build();
        sendMailService.sendTemplateMail(mailBean);
    }
}