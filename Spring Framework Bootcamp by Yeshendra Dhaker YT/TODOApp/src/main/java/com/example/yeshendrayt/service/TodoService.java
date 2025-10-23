package com.example.yeshendrayt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.Todo;
import com.example.yeshendrayt.repository.TodoRepoitory;

@Service
public class TodoService {
	
	@Autowired
	TodoRepoitory todoRepoitory;
	
	public void saveTodo(Todo todo) {
		todoRepoitory.save(todo);
		
	}

	public List<Todo> getAllTodos(){
		return todoRepoitory.findAll();
	}
	
	public void updateTodo(Long id, Todo newTodo) {
		Optional<Todo> oldTodo=todoRepoitory.findTodoById(id);
		if(oldTodo.isPresent()) {
			Todo todoOld=oldTodo.get();
			todoOld.setTaskContent(newTodo.getTaskContent());
			todoOld.setStatus(newTodo.getStatus());
			todoRepoitory.updateTodo(todoOld);
		}
	}
	
	public void deleteTodo(Long id) {
		todoRepoitory.deleteTodoById(id);
	}
	
}
