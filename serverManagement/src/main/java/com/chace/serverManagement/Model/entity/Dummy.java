package com.chace.serverManagement.Model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "t_dummy")
public class Dummy {

  @Id
  @GeneratedValue
  private Long idSample;

  @JdbcTypeCode(SqlTypes.JSON) /* New feature in hibernate 6 (embedded in springBoot 3) to store Complex types as JSON in DB */
  @Column(name = "sample_list", nullable = false, columnDefinition = "JSON")
  private List<String> list;


}