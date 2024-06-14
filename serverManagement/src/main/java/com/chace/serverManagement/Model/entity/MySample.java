package com.chace.serverManagement.Model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_sample")
public class MySample {

  @Id
  @GeneratedValue
  private Long idSample;

  /* How to store collection values to DB */
  @ElementCollection // 1
  @CollectionTable(name = "t_sample_list", joinColumns = @JoinColumn(name = "sample_id")) // 2
  @Column(name = "sample_list") // 3
  private List<String> list;

}