package com.todoapp.service;

import java.util.List;

import com.todoapp.dto.TaskDTO;


public interface ITaskService {

	List<TaskDTO> findAll();
	TaskDTO findById(Long id);
	TaskDTO save(TaskDTO taskDTO);
	TaskDTO update(Long id, TaskDTO taskDTO);
	void delete(Long id);
}
