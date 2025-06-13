package com.todoapp.controller;

import java.util.List;

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

import com.todoapp.dto.ListDTO;
import com.todoapp.service.IListService;

@RestController
@RequestMapping("/api/list")
public class ListController {
	
	@Autowired
	private IListService service;
	
	@GetMapping
	public ResponseEntity<List<ListDTO>> getAll() {
		List<ListDTO> taskList = service.findAll();
		return ResponseEntity.ok(taskList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ListDTO> getById(@PathVariable Long id){
		ListDTO taskList = service.findById(id);
		return ResponseEntity.ok(taskList);
	}
	
	@PostMapping
	public ResponseEntity<ListDTO> save(@RequestBody ListDTO dto) {
		ListDTO saved = service.save(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ListDTO> update(@PathVariable Long id, @RequestBody ListDTO dto) {
		ListDTO updated = service.update(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.ok("deleted");
	}
	
	

}
