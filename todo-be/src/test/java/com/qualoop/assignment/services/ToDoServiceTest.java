package com.qualoop.assignment.services;

import com.qualoop.assignment.exceptions.DataSourceException;
import com.qualoop.assignment.mappers.ToDoMapper;
import com.qualoop.assignment.mappers.ToDoMapperImpl;
import com.qualoop.assignment.models.dtos.ToDoDTO;
import com.qualoop.assignment.models.entities.ToDo;
import com.qualoop.assignment.repositories.ToDoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

  @Spy ToDoMapper mapper = new ToDoMapperImpl();

  @Mock ToDoRepository repository;

  @InjectMocks ToDoService service;

  @Nested
  @DisplayName("Tests for the method create")
  class create {

    @Test
    void create_toDo_entry() throws DataSourceException {
      ToDo responseToDO = new ToDo();
      responseToDO.setActive(true);
      responseToDO.setDescription("to do description");
      ToDoDTO toDoDTO = new ToDoDTO();
      toDoDTO.setActive(responseToDO.getActive());
      toDoDTO.setDescription(responseToDO.getDescription());
      when(repository.save(any(ToDo.class))).thenReturn(responseToDO);
      ToDoDTO responseDto = service.create(toDoDTO);
      assertThat(responseDto)
          .hasFieldOrPropertyWithValue("active", responseDto.getActive())
          .hasFieldOrPropertyWithValue("description", responseDto.getDescription());
    }

    @Test
    void todoRepository_returns_exception() {
      ToDo responseToDO = new ToDo();
      responseToDO.setActive(true);
      responseToDO.setDescription("to do description");
      ToDoDTO toDoDTO = new ToDoDTO();
      toDoDTO.setActive(responseToDO.getActive());
      toDoDTO.setDescription(responseToDO.getDescription());
      when(repository.save(any())).thenThrow(new RuntimeException());
      assertThatThrownBy(
              () -> {
                service.create(toDoDTO);
              })
          .isInstanceOf(DataSourceException.class);
    }
  }

  @Nested
  @DisplayName("Tests for the method read")
  class read {

    @Test
    void read_toDo() {
      ToDo responseToDO = new ToDo();
      responseToDO.setActive(true);
      responseToDO.setDescription("to do description");
      ToDoDTO toDoDTO = new ToDoDTO();
      toDoDTO.setActive(responseToDO.getActive());
      toDoDTO.setDescription(responseToDO.getDescription());
      when(repository.findById(any(Integer.class))).thenReturn(Optional.of(responseToDO));
      assertThat(service.read(10)).isEqualTo(toDoDTO);
    }

    @Test
    void toDo_not_found() {
      ToDo responseToDO = new ToDo();
      responseToDO.setActive(true);
      responseToDO.setDescription("to do description");
      ToDoDTO toDoDTO = new ToDoDTO();
      toDoDTO.setActive(responseToDO.getActive());
      toDoDTO.setDescription(responseToDO.getDescription());
      when(repository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
      assertThatThrownBy(() -> service.read(10)).isInstanceOf(EntityNotFoundException.class);
    }
  }

  @Nested
  @DisplayName("Tests for the method readAll")
  class readAll {

    @Test
    void readAll_ToDos() {
      ToDo responseToDO = new ToDo();
      responseToDO.setActive(true);
      responseToDO.setDescription("to do description");
      ToDo responseToDO2 = new ToDo();
      responseToDO2.setActive(false);
      responseToDO2.setDescription("to do another description");
      ToDoDTO toDoDTO = new ToDoDTO();
      toDoDTO.setActive(responseToDO.getActive());
      toDoDTO.setDescription(responseToDO.getDescription());
      ToDoDTO toDoDTO2 = new ToDoDTO();
      toDoDTO2.setActive(responseToDO2.getActive());
      toDoDTO2.setDescription(responseToDO2.getDescription());
      when(repository.findAll()).thenReturn(Arrays.asList(responseToDO, responseToDO2));
      assertThat(service.readAll()).isEqualTo(Arrays.asList(toDoDTO, toDoDTO2));
    }
  }

  @Nested
  @DisplayName("Tests for the method delete")
  class delete {

    @Test
    void toDoRepository_throws_exception() {
      doThrow(new RuntimeException()).when(repository).deleteById(any());
      assertThatThrownBy(() -> service.delete(10)).isInstanceOf(DataSourceException.class);
    }
  }
}
