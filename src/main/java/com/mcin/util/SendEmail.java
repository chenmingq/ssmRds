package com.mcin.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by Mcin on 2017/3/9.
 *
 * 发送邮件
 */
public class SendEmail {


    public static final String HOST = "smtp.163.com"; // 发送邮件的服务器地址
    public static final String PROTOCOL = "smtp"; // 发送邮件的协议
    public static final int PORT = 25;
    public static final String EMAIL_FROM = "163@163.com";//发件人的email
    public static final String EMAIL_PWD = "123456";//发件人密码

    /**
     * 获取Session
     * @return
     */
    public static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , true);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PWD);
            }
        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }


//    public static void send(String toEmail , String content) {
//        Session session = getSession();
//        try {
//
//            System.out.println("--send--"+content);
//            // Instantiate a message
//            Message msg = new MimeMessage(session);
//
//            //Set message attributes
//            msg.setFrom(new InternetAddress(FROM));
//            InternetAddress[] address = {new InternetAddress(toEmail)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject("账号激活邮件");
//            msg.setSentDate(new Date());
//            msg.setContent(content , "text/html;charset=utf-8");
//            //Send the message
//            Transport.send(msg);
//        } catch (MessagingException e) {
//            LogInfo.error("邮件发送出现异常 ----------- ：",e.getMessage());
//            e.printStackTrace();
//        }
//    }

}
