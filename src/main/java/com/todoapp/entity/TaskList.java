package com.todoapp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
@Builder
public class TaskList {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "taskList", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Task> tasks;
	
	@OneToMany(mappedBy = "taskList", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Task> tasks;
	
}
