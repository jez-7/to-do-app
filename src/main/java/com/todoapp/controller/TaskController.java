package com.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.entity.Task;
import com.todoapp.entity.Task.State;
import com.todoapp.service.ITaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private ITaskService taskService;
	
	@GetMapping
	public ResponseEntity<List<Task>> getAll(){
		List<Task> getAll = taskService.findAll();
		return ResponseEntity.ok(getAll);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getById(@PathVariable Long id) {
		Task getById = taskService.findById(id);
		return ResponseEntity.ok(getById);
	}
	
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task){
		
		Task create = taskService.save(task); 
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task){
		Task update = taskService.update(id, task);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
