package com.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todoapp.entity.Task.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

	private Long id;
	private String name;
	private String description;
	private State state = State.CREATED;
	@JsonProperty("taskListId")
	private Long taskList;
}
