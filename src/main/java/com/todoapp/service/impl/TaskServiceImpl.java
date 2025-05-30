package com.todoapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.dto.TaskDTO;
import com.todoapp.entity.Task;
import com.todoapp.repository.ITaskRepository;
import com.todoapp.service.ITaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements ITaskService{
	
	@Autowired
	private ITaskRepository taskRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TaskDTO> findAll() {
		
		return taskRepository.findAll()
				.stream()
				.map(entity -> modelMapper.map(entity, TaskDTO.class))
				.collect(Collectors.toList())
				;
	}

	@Transactional
	@Override
	public TaskDTO findById(Long id) {
		return modelMapper.map(taskRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Task not found with id: " + id)),
				TaskDTO.class);
	}

	@Transactional
	@Override
	public TaskDTO save(TaskDTO taskDTO) {
		try {
			Task task = modelMapper.map(taskDTO, Task.class);
			return modelMapper.map(taskRepository.save(task), TaskDTO.class);
		} catch (Exception e) {
			throw new UnsupportedOperationException("Error");
		}

	}
	
	@Transactional
	@Override
	public TaskDTO update(Long id, TaskDTO taskDTO) {
		
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
		
		modelMapper.map(taskDTO, task);
		Task taskUpdate = taskRepository.save(task);
		
		return modelMapper.map(taskUpdate, TaskDTO.class);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}

}
