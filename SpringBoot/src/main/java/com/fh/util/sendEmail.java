package com.fh.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class sendEmail {
    public static void sendEmail(String to,String title,String content) throws Exception{
        Properties props = new Properties();//key value:配置参数。真正发送邮件时再配置
        props.setProperty("mail.transport.protocol", "smtp");//指定邮件发送的协议，参数是规范规定的
        props.setProperty("mail.host", "smtp.163.com");//指定发件服务器的地址，参数是规范规定的
//        props.setProperty("mail.debug", "true");//邮件发送的调试模式，参数是规范规定的
        props.setProperty("mail.smtp.auth", "true");//请求服务器进行身份认证。参数与具体的JavaMail实现有关

        Session session = Session.getInstance(props);//发送邮件时使用的环境配置
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);

        //设置邮件的头
        message.setFrom(new InternetAddress("hhg_0614@163.com"));
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(title);
        //设置正文
        message.setContent(content, "text/html;charset=utf-8");
        //message.setText("<h1>hello</h1>");//纯文本

        message.saveChanges();

        //发送邮件
        Transport ts = session.getTransport();
        ts.connect("hhg_0614@163.com", "OKIADSGIYSTENULF");       // 密码为授权码不是邮箱的登录密码
        ts.sendMessage(message, message.getAllRecipients());//对象，用实例方法}

    }


    public static void main(String[] args) throws Exception{
        sendEmail("hhg_0614@163.com","测试邮件头","你自己那么努力怎么可能没有回报呢！");
    }
}
