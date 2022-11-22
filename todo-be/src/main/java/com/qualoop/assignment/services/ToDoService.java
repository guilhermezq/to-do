package com.qualoop.assignment.services;

import com.qualoop.assignment.exceptions.DataSourceException;
import com.qualoop.assignment.mappers.ToDoMapper;
import com.qualoop.assignment.models.dtos.ToDoDTO;
import com.qualoop.assignment.models.entities.ToDo;
import com.qualoop.assignment.repositories.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Log4j2
public class ToDoService {

  private final ToDoMapper mapper;
  private final ToDoRepository repository;

  public ToDoDTO create(ToDoDTO dto) {
    try {
      repository.save(mapper.dtoToEntity(dto));
      return dto;
    } catch (Exception e) {
      log.error("Datasource Error on creating ToDo");
      throw new DataSourceException();
    }
  }

  public ToDoDTO read(Integer toDoId) {
    ToDo toDo = repository.findById(toDoId).orElseThrow(EntityNotFoundException::new);
    return mapper.entityToDto(toDo);
  }

  public List<ToDoDTO> readAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
        .map(mapper::entityToDto)
        .collect(Collectors.toList());
  }

  public void delete(Integer toDoId) {
    try {
      repository.deleteById(toDoId);
    } catch (Exception e) {
      log.error("Datasource Error on deleting ToDo");
      throw new DataSourceException();
    }
  }

  public ToDoDTO update(Integer toDoId, ToDoDTO dto) {
    Optional<ToDo> toDoOptional = repository.findById(toDoId);
    ToDo toDo = toDoOptional.orElseThrow(EntityNotFoundException::new);
    return mapper.entityToDto(repository.save(mapper.merge(dto, toDo)));
  }
}
