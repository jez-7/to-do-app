package com.todoapp.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.todoapp.entity.Task;
import com.todoapp.entity.TaskList;

@Configuration
public class ModelMapperConfig {


	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper mapper = new ModelMapper();
		
    // Task map
		mapper.typeMap(Task.class, TaskDTO.class)
        		.addMapping(src -> src.getTaskList().getId(), TaskDTO::setTaskList);

		mapper.typeMap(TaskDTO.class, Task.class)
				    .addMappings(mapping -> mapping.skip(Task::setTaskList));
		
    // ListTask map
    mapper.typeMap(ListDTO.class, TaskList.class)
            .addMappings(mapper -> mapper.skip(TaskList::setId));
		return mapper;
	}
}



