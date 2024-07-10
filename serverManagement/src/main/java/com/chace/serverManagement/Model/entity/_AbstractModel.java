package com.chace.serverManagement.Model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Data @MappedSuperclass //A class designated with the MappedSuperclass annotation can be mapped in the same way as an entity except that the mappings will apply only to its subclasses since no table exists for the mapped superclass itself.
public abstract class _AbstractModel {

  //  @CreationTimestamp // from hibernate
//  @CreatedDate // from JPA packed within spring
  @Column(name = "creation_date", updatable = false)
  protected ZonedDateTime creationDate;

  //  @UpdateTimestamp  // from hibernate
//  @LastModifiedDate // from JPA packed within spring
  @Column(name = "last_modified_date")
  protected ZonedDateTime lastModifiedDate;


  @PrePersist
  protected void prePersist() {
    if (this.creationDate == null)  creationDate = ZonedDateTime.now();
    if (this.lastModifiedDate == null) lastModifiedDate = ZonedDateTime.now();
  }

  @PreUpdate
  protected void preUpdate() {
    this.lastModifiedDate = ZonedDateTime.now();
  }

  @PreRemove
  protected void preRemove() {
    this.lastModifiedDate = ZonedDateTime.now();
  }

}
