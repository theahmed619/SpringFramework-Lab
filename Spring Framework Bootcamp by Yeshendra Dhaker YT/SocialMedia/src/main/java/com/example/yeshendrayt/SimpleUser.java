package com.example.yeshendrayt;

public class SimpleUser implements User{
	
	String userName;
	PostList postList;
	
	public void init() {
		System.out.println("DB Connection Successfull");
	}
	
	public void destroy() {
		System.out.println("Closed all the resources");
	}

	@Override
	public void setUserName(String userName) {
		this.userName=userName;
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public void setPostList(PostList postList) {
		
		this.postList=postList;
		
	}

	@Override
	public PostList getPostList() {
		
		return this.postList;
	}

}
