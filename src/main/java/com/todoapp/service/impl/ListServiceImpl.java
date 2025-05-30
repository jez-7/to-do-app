package com.todoapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.dto.ListDTO;
import com.todoapp.entity.TaskList;
import com.todoapp.repository.IListRepository;
import com.todoapp.service.IListService;

@Service
public class ListServiceImpl implements IListService{
	
	@Autowired
	private IListRepository repository;
	@Autowired
	private ModelMapper modelMapper;

	 
	public ListDTO findById(Long id) {
		TaskList entity = repository.findById(id)
		        .orElseThrow(() -> new RuntimeException("TaskList not found with id " + id));
		    return modelMapper.map(entity, ListDTO.class);
	}

	 
	public List<ListDTO> findAll() {	 
		return repository.findAll()
				.stream()
				.map(entity -> modelMapper.map(entity, ListDTO.class))
				.collect(Collectors.toList());
	}

	public ListDTO save(ListDTO dto) {
		 TaskList entity = modelMapper.map(dto, TaskList.class);
		 TaskList saved = repository.save(entity);
		 return modelMapper.map(saved, ListDTO.class);
	}

	public ListDTO update(Long id, ListDTO dto) {
	    TaskList entity = repository.findById(id)
	        .orElseThrow(() -> new RuntimeException("TaskList not found with id " + id));

	    modelMapper.map(dto, entity);

	    TaskList updated = repository.save(entity);
	    return modelMapper.map(updated, ListDTO.class);
	}


	@Override
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
	        throw new RuntimeException("TaskList not found with id " + id);
	    }
	    repository.deleteById(id);
		
	}
 


	 
	 
	

}
