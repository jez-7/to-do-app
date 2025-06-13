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

import com.todoapp.dto.TaskDTO;
import com.todoapp.service.ITaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private ITaskService taskService;
	
	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAll(){
		List<TaskDTO> getAll = taskService.findAll();
		return ResponseEntity.ok(getAll);
	}

	@GetMapping("/list/{taskListId}")
	public ResponseEntity<List<TaskDTO>> getTasksByTaskListId(@PathVariable Long taskListId) {
		List<TaskDTO> tasks = taskService.findTaskByTaskListId(taskListId);
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getById(@PathVariable Long id) {
		TaskDTO getById = taskService.findById(id);
		return ResponseEntity.ok(getById);
	}
	
	@PostMapping
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO){
		
		TaskDTO create = taskService.save(taskDTO); 
		return ResponseEntity.status(HttpStatus.CREATED).body(create);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
		TaskDTO update = taskService.update(id, taskDTO);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@PutMapping("changeState/{id}")
	public ResponseEntity<TaskDTO> changeState(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
		TaskDTO changeState = taskService.changeState(id, taskDTO);
		return ResponseEntity.ok(changeState);

		 
	}
}
