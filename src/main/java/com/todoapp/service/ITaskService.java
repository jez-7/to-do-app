package com.todoapp.service;

import java.util.List;

import com.todoapp.entity.Task;

public interface ITaskService {

	List<Task> findAll();
	Task findById(Long id);
	Task save(Task task);
	Task update(Long id, Task task);
	void delete(Long id);
}
