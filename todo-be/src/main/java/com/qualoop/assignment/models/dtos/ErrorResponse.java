package com.qualoop.assignment.models.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {
  private final String message;
  private final List<String> errors;


}
