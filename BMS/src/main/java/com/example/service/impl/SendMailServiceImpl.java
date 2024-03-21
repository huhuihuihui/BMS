package com.example.service.impl;

import cn.hutool.extra.template.TemplateEngine;
import com.example.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ITemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMail(String activationUrl, String mail) {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
            message.setSubject("图书管理系统----个人账户激活");
            message.setFrom(from);
            message.setTo(mail);
            message.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("activationUrl",activationUrl);
            String text = templateEngine.process("activation-account.html",context);
            message.setText(text,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
