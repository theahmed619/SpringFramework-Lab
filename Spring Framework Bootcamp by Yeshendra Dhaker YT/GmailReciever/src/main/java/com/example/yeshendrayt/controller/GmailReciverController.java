package com.example.yeshendrayt.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.yeshendrayt.entity.Email;
import com.example.yeshendrayt.entity.EmailRequest;
import com.example.yeshendrayt.service.GmailReciverService;

@Controller
public class GmailReciverController {

	@Autowired
	GmailReciverService gmailReciverService;
	
	private RestTemplate restTemplate;
	
	public GmailReciverController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}

	   @GetMapping("/")	 
	    public String inbox(Model model) {
	       List<Email> emailList = gmailReciverService.getEmails();
	        model.addAttribute("emailslist", emailList);
	        return "inbox";  // Make sure you have a Thymeleaf template named 'inbox.html'
	    }
	   @PostMapping("/sendmail")
	   public String sendMail(@ModelAttribute EmailRequest emailRequest, Model model) {
		   try {
		   String url="http://localhost:8090/sendmail";
		   HttpHeaders headers= new  HttpHeaders();
		   headers.setContentType(MediaType.APPLICATION_JSON);
		   HttpEntity<EmailRequest> httpEntity=new  HttpEntity<EmailRequest>(emailRequest,headers);
		   ResponseEntity<String> response=restTemplate.postForEntity(url, httpEntity, String.class);
		   if(response.getStatusCode()==HttpStatus.OK) {
			   model.addAttribute("success","Email sent successfully");
			   
		   }else {
			   model.addAttribute("failed","Failed to send email");
		   }
		   }catch (Exception e) {
			model.addAttribute("error","Process failed"+ e.getMessage());
		}
		   return "inbox";
	   }
	   
//	   @GetMapping("/hello")
//	    public String hello(Model model) {
//	        List<Email> emailList = gmailReciverService.getEmails();
//	        model.addAttribute("emailslist", emailList);
//	        return "hello";  // Make sure you have a Thymeleaf template named 'inbox.html'
//	    }


	@GetMapping("/test")
	@ResponseBody
	public String testApp() {
		return "App is running!";
	}

//	@GetMapping("/fetch")
//	@ResponseBody
//	public String fetchMails() {
//		gmailReciverService.initialMails();
//		return "Fetched emails!";
//	}
	
//	@GetMapping("/fetch")
//	public String fetchMailsAndRedirect() {
//	    try {
//	        System.out.println("Fetching Mails...");
//	        gmailReciverService.initialMails();  // Fetch emails here
//	    } catch (Exception e) {
//	        System.out.println("Error fetching mails: " + e.getMessage());
//	        return "Error fetching emails: " + e.getMessage();
//	    }
//
//	    return "redirect:/";
//	}


}
