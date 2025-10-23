package com.example.yeshendrayt;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class SocialMediaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SocialMediaApplication.class, args);
		
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		Scanner scanner=new Scanner(System.in);
		User user =(User)context.getBean("user");
		
		System.out.println("Enter your Username:\n");
		String username=scanner.next();
		
		user.setUserName(username);
		
		PostList postList=(PostList)context.getBean("postList");
		while(true) {
			
			
			System.out.println("Choose from below\n1. Create a Post\n2. See All post\n3. EXIT");
			int userSelec=scanner.nextInt();
			
			switch (userSelec) {
			case 1:{
				Post post =(Post) context.getBean("post");
				scanner.nextLine();
				String message=scanner.nextLine();
				post.setMessage(message);
				postList.setPost(post);
				user.setPostList(postList);
				break;
			}
				
			

			case 2:{
				postList.getAllPost().forEach(item-> System.out.println(item.getMessage()));
			
				break;
			}
//			case 3:{
//				System.out.println(user.getPostList().toString());
//				
//				break;
//			}
			case 3:{
				context.close();
			}
			}
			
		}
		
		
		
	}

}
