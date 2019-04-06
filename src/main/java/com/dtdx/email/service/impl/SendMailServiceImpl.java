package com.dtdx.email.service.impl;

import com.dtdx.email.domain.MailBean;
import com.dtdx.email.service.SendMailService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;

/**
 * @ClassName SendMailServiceImpl
 * @Description TODO
 * @Author huawei
 * @Date 2019/4/6 21:16
 * @Version 1.0
 **/
@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private Configuration configuration;

    /**
     * 发送简单格式邮件
     * @param mailBean
     */
    @Override
    public void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(mailBean.getRecipient());
            simpleMailMessage.setSubject(mailBean.getSubject());
            simpleMailMessage.setText(mailBean.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.error("邮件发送失败！{}",e);
        }
    }

    @Override
    public void sendHtmlMail(MailBean mailBean)  {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
                    .append("<p style='color:#F00'>"+mailBean.getContent()+"</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            mimeMessageHelper.setText(sb.toString(),true);
            javaMailSender.send(mimeMessage);
        }  catch (Exception e) {
            log.error("邮件发送失败！{}",e);
        }


    }

    /**
     * 发送带附件的邮件
     * @param mailBean
     */
    @Override
    public void sendAttachmentMail(MailBean mailBean) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
                    .append("<p style='color:#F00'>"+mailBean.getContent()+"</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            mimeMessageHelper.setText(sb.toString(),true);
            File file = FileUtils.getFile("D:\\Downloads\\images\\mm.jpg");
            mimeMessageHelper.addAttachment("mm.jpg",file);
            javaMailSender.send(mimeMessage);
        }  catch (Exception e) {
            log.error("邮件发送失败！{}",e);
        }

    }

    /**
     * 发送带静态资源的邮件
     * @param mailBean
     */
    @Override
    public void sendInlineMail(MailBean mailBean) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
                    .append("<p style='color:#F00'>"+mailBean.getContent()+"</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            mimeMessageHelper.setText(sb.toString(),true);
            File file = FileUtils.getFile("D:\\Downloads\\images\\mm.jpg");
            mimeMessageHelper.addInline("picture",file);
            javaMailSender.send(mimeMessage);
        }  catch (Exception e) {
            log.error("邮件发送失败！{}",e);
        }

    }

    @Override
    public void sendTemplateMail(MailBean mailBean) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            HashMap<String,Object> model = new HashMap<>();
            model.put("content",mailBean.getContent());
            model.put("title","标题Mail中使用了FreeMarker");
            Template template = configuration.getTemplate("mail.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            System.out.println(text);
            mimeMessageHelper.setText(text,true);
            javaMailSender.send(mimeMessage);
        }  catch (Exception e) {
            log.error("邮件发送失败！{}",e);
        }

    }
}
