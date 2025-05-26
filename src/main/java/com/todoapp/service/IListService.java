package com.todoapp.service;

import java.util.List;
import java.util.Optional;

import com.todoapp.entity.TaskList;

public interface IListService {
	
	Optional<TaskList> findById(Long id);
	List<TaskList> findAll();
	TaskList save(TaskList taskList);
	TaskList update(Long id, TaskList taskList);
	void deleteById(Long id);

}
