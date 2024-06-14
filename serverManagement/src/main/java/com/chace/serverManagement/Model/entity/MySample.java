package com.chace.serverManagement.Model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sample")
public class MySample {

  @Id
  @GeneratedValue
  private Long id;

  @ElementCollection // 1
  @CollectionTable(name = "my_list", joinColumns = @JoinColumn(name = "sample_id")) // 2
  @Column(name = "list") // 3
  private List<String> list;

}