package com.todoapp.service;

import java.util.List;

import com.todoapp.dto.ListDTO;

public interface IListService {
	
	ListDTO findById(Long id);
	List<ListDTO> findAll();
	ListDTO save(ListDTO listDTO);
	ListDTO update(Long id, ListDTO dto);
	void deleteById(Long id);

}
