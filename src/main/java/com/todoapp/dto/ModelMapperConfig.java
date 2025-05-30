package com.todoapp.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.todoapp.entity.TaskList;

@Configuration
public class ModelMapperConfig {
    @Bean
    ModelMapper modelMapper() {
    	ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(ListDTO.class, TaskList.class)
            .addMappings(mapper -> mapper.skip(TaskList::setId));
        return modelMapper;
    }
}