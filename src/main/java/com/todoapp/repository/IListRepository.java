package com.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.entity.TaskList;

public interface IListRepository extends JpaRepository<TaskList, Long> {

}
