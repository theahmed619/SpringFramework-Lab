package com.example.yeshendrayt;

import java.util.ArrayList;
import java.util.List;

public class SimplePostList implements PostList{
	
	List<Post> listOfPost=null;
	
	public SimplePostList() {
		this.listOfPost=new ArrayList<>();
	}

	@Override
	public void setPost(Post post) {
		listOfPost.add(post);
		
	}

	@Override
	public List<Post> getAllPost() {
		
		return this.listOfPost;
	}

}
