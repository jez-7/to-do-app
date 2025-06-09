package com.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.entity.Task;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTaskByTaskListId(Long taskListId);
}
