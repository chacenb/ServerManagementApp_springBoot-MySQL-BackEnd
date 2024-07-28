package com.chace.serverManagement.Model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true) @Data @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "t_dummy")
public class Dummy extends _AbstractModel {
  private String name;
  private String description;

}