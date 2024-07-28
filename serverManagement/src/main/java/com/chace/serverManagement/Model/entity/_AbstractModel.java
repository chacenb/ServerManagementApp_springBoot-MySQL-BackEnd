package com.chace.serverManagement.Model.entity;

import com.chace.serverManagement.configurations.securityConfiguration.UserPrincipal;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter @Setter @ToString @MappedSuperclass //A class designated with the MappedSuperclass annotation can be mapped in the same way as an entity except that the mappings will apply only to its subclasses since no table exists for the mapped superclass itself.
public abstract class _AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  //  @CreationTimestamp // from hibernate
//  @CreatedDate // from JPA packed within spring
  @Column(name = "creation_date", updatable = false)
  protected ZonedDateTime creationDate;

  //  @UpdateTimestamp  // from hibernate
//  @LastModifiedDate // from JPA packed within spring
  @Column(name = "last_modified_date")
  protected ZonedDateTime lastModifiedDate;

  //  @UpdateTimestamp  // from hibernate
//  @LastModifiedDate // from JPA packed within spring
  @Column(name = "deleted_date")
  protected ZonedDateTime deletedDate;

  @Column(name = "is_not_deleted")
  protected Boolean isNotdeleted = true;

  @Column(name = "created_by")
  protected Long createdBy;

  @Column(name = "modified_by")
  protected Long modifiedBy;

  @Column(name = "deleted_by")
  protected Long deletedBy;

  @PrePersist
  protected void prePersist() {
    if (this.creationDate == null) creationDate = ZonedDateTime.now();
    if (this.lastModifiedDate == null) lastModifiedDate = ZonedDateTime.now();

    this.isNotdeleted = true;

    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserPrincipal currPrincpl = (UserPrincipal) authentication.getPrincipal();
      this.createdBy = currPrincpl.getUserId();
    }
  }

  @PreUpdate
  protected void preUpdate() {
    this.lastModifiedDate = ZonedDateTime.now();

    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserPrincipal currPrincpl = (UserPrincipal) authentication.getPrincipal();
      this.modifiedBy = currPrincpl.getUserId();
    }
  }

  @PreRemove
  protected void preRemove() {
    this.lastModifiedDate = ZonedDateTime.now();
    this.deletedDate = ZonedDateTime.now();

    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserPrincipal currPrincpl = (UserPrincipal) authentication.getPrincipal();
      this.deletedBy = currPrincpl.getUserId();
    }

  }


//  @Override
//  public String toString() {
//    return "_AbstractModel {" +
//      "id=" + id +
//      ", creationDate=" + creationDate +
//      ", lastModifiedDate=" + lastModifiedDate +
//      ", deletedDate=" + deletedDate +
//      ", createdBy=" + createdBy +
//      ", modifiedBy=" + modifiedBy +
//      ", deletedBy=" + deletedBy +
//      '}';
//  }
}
