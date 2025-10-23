package com.example.yeshendrayt;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class VotingAppApplication {

	public static void main(String[] args) {
		//SpringApplication.run(VotingAppApplication.class, args);
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.example.yeshendrayt");
		
		
		AuthorityCounter authorityCounter=(AuthorityCounter)context.getBean("authcounter");
		
		while(true) {
		
		System.out.println("Welcome !!!!");
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Choose below: \n1. I wanna Vote\n2. See All Votes(ADMIN)");
		int userInput=scanner.nextInt();
		String beanId="";
		switch(userInput) {
		case 1:{
			
			System.out.println("Enter your username: ");
			scanner.nextLine();
			String userName=scanner.nextLine();
			User user=(User)context.getBean("user");
			user.setUserName(userName);
			
			System.out.println("Choose the party you want to vote for: \n1. Democratic\n2. Republic\n3. Independent");
			int userPartySelect=scanner.nextInt();
			switch(userPartySelect) {
			case 1:{
				beanId="democratic";
				break;
			}
			case 2:{
				beanId="republic";
				break;
			}
			case 3:{
				beanId="independent";
				break;
			}
			}
			
			PoliticalParty party=(PoliticalParty)context.getBean(beanId);
			user.setPoliticalParty(party);
			UserList userList=(UserList)context.getBean("userlist");
			userList.addUser(user);
			//authorityCounter.setUserList(userList);
			
			System.out.println("Thank you");
			break;
		}
		case 2:{
			authorityCounter.getUserList().getUsersList().forEach(item->System.out.println(item.getUserName()+" is voted for"+item.getPoliticalParty().getPartyName()));
			context.close();
			break;
		}
		}
		}
	}

}
