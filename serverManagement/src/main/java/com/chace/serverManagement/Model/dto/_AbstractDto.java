package com.chace.serverManagement.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;


@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
@SuperBuilder
public abstract class _AbstractDto {
  protected Long          _id;
  protected ZonedDateTime creationDate;
  protected ZonedDateTime lastModifiedDate;
  protected ZonedDateTime deletedDate;
  protected Boolean       isNotDeleted;
  protected Long          createdBy;
  protected Long          modifiedBy;
  protected Long          deletedBy;


  @Override
  public String toString() {
    return "_AbstractDto {" +
      "_id=" + _id +
      ", creationDate=" + creationDate +
      ", lastModifiedDate=" + lastModifiedDate +
      ", createdBy=" + createdBy +
      ", modifiedBy=" + modifiedBy +
      ", deletedBy=" + deletedBy +
      '}';
  }
}
