package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.yeshendrayt.model.GmailSender;
import com.example.yeshendrayt.service.GmailSenderService;

@Controller
public class GmailSenderController {

	@Autowired
	GmailSenderService gmailSenderService;
	
	@GetMapping("/")
	public String GmailForm() {
		return "GmailSenderForm";
	}
	
	@PostMapping("/sendmail")
	@ResponseBody
	public String sendMail(@RequestBody GmailSender gmailSender) {
		
		gmailSenderService.sendMail(gmailSender);
		
		return "sent";
	}
}
