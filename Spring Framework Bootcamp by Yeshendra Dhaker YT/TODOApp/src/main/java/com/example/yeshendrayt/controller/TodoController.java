package com.example.yeshendrayt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.yeshendrayt.entity.Todo;
import com.example.yeshendrayt.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
//	@RequestMapping
//	public String loadForm() {
//		return "task";
//	}
	
	@RequestMapping("/")
	public String getAllTodo(Model model) {
		List<Todo> listOfTodos=todoService.getAllTodos();	
		model.addAttribute("todos",listOfTodos);
		return "task";
	}
	
	
		@RequestMapping(value = "addtodo", method = RequestMethod.POST)
		@ResponseBody
		public String createTodo(@ModelAttribute Todo todo) {
			
			todoService.saveTodo(todo);
			return "success";
		}
		
		@RequestMapping(value = "/updatetodo/{id}")
		public String updateTodo(@PathVariable("id") Long id, @ModelAttribute Todo todo) {
			todoService.updateTodo(id,todo);
			return "redirect:/";
			}
		
		@RequestMapping("/deletetodo/{id}")
		public String deleteTodo(@PathVariable("id") Long id) {
			todoService.deleteTodo(id);
			return "redirect:/";
			
		}


}
