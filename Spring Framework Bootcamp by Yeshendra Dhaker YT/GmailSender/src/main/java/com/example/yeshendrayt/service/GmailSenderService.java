package com.example.yeshendrayt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.model.GmailSender;

@Service
public class GmailSenderService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendMail(GmailSender gmailSender){
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(gmailSender.getTo());
		message.setSubject(gmailSender.getSubject());
		message.setText(gmailSender.getMessage());
		
		javaMailSender.send(message);
		
	}

}
