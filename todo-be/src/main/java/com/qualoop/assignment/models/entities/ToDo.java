package com.qualoop.assignment.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ToDo {

  @Id @GeneratedValue private Integer id;

  private Boolean active;
  private String description;
}
