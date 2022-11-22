package com.qualoop.assignment.repositories;

import com.qualoop.assignment.models.entities.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Integer> {
}
