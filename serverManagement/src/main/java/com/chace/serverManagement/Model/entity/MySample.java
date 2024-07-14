package com.chace.serverManagement.Model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_sample")
public class MySample {

  @Id
  @GeneratedValue
  private Long idSample;

  /* How to store collection values to DB : WAY 1 */
//  @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
//  @CollectionTable(name = "t_sample_list", joinColumns = @JoinColumn(name = "sample_id")) // 2
//  @Column(name = "sample_list", nullable = false) // 3
//  private List<String> list;


  @JdbcTypeCode(SqlTypes.JSON) /* New feature in hibernate 6 (embedded in springBoot 3) to store Complex types as JSON in DB */
  @Column(name = "sample_list", nullable = false, columnDefinition = "JSON")
  private List<String> list;


}