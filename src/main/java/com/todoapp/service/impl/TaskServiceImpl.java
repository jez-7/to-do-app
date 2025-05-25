package com.todoapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.entity.Task;
import com.todoapp.repository.ITaskRepository;
import com.todoapp.service.ITaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements ITaskService{
	
	@Autowired
	private ITaskRepository taskRepository;

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Transactional
	@Override
	public Task findById(Long id) {
		return taskRepository.findById(id).
				orElse(null);
	}

	@Transactional
	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}
	
	@Transactional
	@Override
	public Task update(Long id, Task task) {
		task.setId(id);
		return taskRepository.save(task);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}

}
