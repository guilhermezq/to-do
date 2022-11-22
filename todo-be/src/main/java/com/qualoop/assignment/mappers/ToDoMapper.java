package com.qualoop.assignment.mappers;

import com.qualoop.assignment.models.dtos.ToDoDTO;
import com.qualoop.assignment.models.entities.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ToDoMapper {

  ToDoDTO entityToDto(ToDo toDo);

  ToDo dtoToEntity(ToDoDTO toDoDTO);

  ToDo merge(ToDoDTO source, @MappingTarget ToDo target);
}
