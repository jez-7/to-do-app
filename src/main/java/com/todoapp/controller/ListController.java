package com.todoapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.entity.TaskList;
import com.todoapp.service.IListService;

@RestController
@RequestMapping("/api/list")
public class ListController {
	
	@Autowired
	private IListService service;
	
	@GetMapping
	public ResponseEntity<List<TaskList>> getAll() {
		List<TaskList> taskList = service.findAll();
		return ResponseEntity.ok(taskList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<TaskList>> getById(@PathVariable Long id){
		Optional<TaskList> taskList = service.findById(id);
		return ResponseEntity.ok(taskList);
	}
	
	@PostMapping
	public ResponseEntity<TaskList> save(@RequestBody TaskList newList) {
		TaskList taskList = service.save(newList);
		return new ResponseEntity<>(taskList, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskList> update(@PathVariable Long id, @RequestBody TaskList editedList) {
		TaskList taskList = service.update(id, editedList);
		return ResponseEntity.ok(taskList);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok("deleted");
	}
	
	

}
