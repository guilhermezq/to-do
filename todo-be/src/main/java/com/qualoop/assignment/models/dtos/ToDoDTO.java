package com.qualoop.assignment.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDoDTO {

  private Integer id;
  private Boolean active = false;
  @NotBlank(message = "description is mandatory")
  private String description;
}
