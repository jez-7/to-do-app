package com.todoapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.entity.TaskList;
import com.todoapp.repository.IListRepository;
import com.todoapp.service.IListService;

@Service
public class ListServiceImpl implements IListService{
	
	@Autowired
	private IListRepository repository;

	@Override
	public Optional<TaskList> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public List<TaskList> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public TaskList save(TaskList taskList) {
		// TODO Auto-generated method stub
		return repository.save(taskList);
	}

	@Override
	public TaskList update(Long id, TaskList taskList) {
		// TODO Auto-generated method stub
		taskList.setId(id);
		return repository.save(taskList);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}
	
	 
	

}
