package com.portpolio.MyPortpolio.Service.Impl;



import com.portpolio.MyPortpolio.DTO.MyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledServiceImpl {
    @Value("${my.portfolio.id}")
    private String email;
    @Value("${id.password}")
    private String password;
    @Value("${portfolio.api}")
    private String api;
    @Value("${spring.mail.username}")
    private String senderMail;
    @Autowired
    private  RestTemplate restTemplate;
    private JavaMailSender javaMailSender;

    public ScheduledServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Scheduled(fixedRate = 1800000)
    public void connect(){
        MyRequest myRequest = new MyRequest(email,password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MyRequest> entity = new HttpEntity<>(myRequest,headers);
        try {
            restTemplate.postForObject(api, entity,String.class);
            System.out.println("success");
        } catch (RestClientException e) {
            System.err.println("API call failed: " + e.getMessage());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setFrom(senderMail);
            mailMessage.setSubject("Api call failed please check render");
            mailMessage.setText("Please check the service on render getting error while trying to hit api: " + e.getMessage());
            javaMailSender.send(mailMessage);
        }
    }

}
