package com.qualoop.assignment.controllers;

import com.qualoop.assignment.models.dtos.ErrorResponse;
import com.qualoop.assignment.models.dtos.ToDoDTO;
import com.qualoop.assignment.services.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/todo")
public class ToDoController {

  private final ToDoService service;

  @PostMapping("")
  public ResponseEntity<ToDoDTO> create(@Valid @RequestBody ToDoDTO dto) {
    return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
  }

  @GetMapping("")
  public ResponseEntity<List<ToDoDTO>> readAll() {
    return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
  }

  @GetMapping("/{toDoId}")
  public ResponseEntity<ToDoDTO> read(@PathVariable Integer toDoId) {
    return new ResponseEntity<>(service.read(toDoId), HttpStatus.OK);
  }

  @DeleteMapping("/{toDoId}")
  public ResponseEntity<Boolean> delete(@PathVariable Integer toDoId) {
    service.delete(toDoId);
    return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/{toDoId}")
  public ResponseEntity<ToDoDTO> update(@PathVariable Integer toDoId, @RequestBody ToDoDTO dto) {
    return new ResponseEntity<>(service.update(toDoId, dto), HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleNotValid(MethodArgumentNotValidException e) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .message("Validation Error")
            .errors(
                e.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()))
            .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .message("Internal Error")
            .errors(Arrays.asList("Try again later"))
            .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
