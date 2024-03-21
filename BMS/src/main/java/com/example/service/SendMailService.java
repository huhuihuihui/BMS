package com.example.service;

public interface SendMailService {
    /*
    激活账号邮件发送
    activationUrl, 激活 url 链接
    String mail    收件人邮箱
     */
    void sendMail(String activationUrl, String mail);
}
