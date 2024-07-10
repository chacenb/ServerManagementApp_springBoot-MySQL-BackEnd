package com.chace.serverManagement.Model.dto_notUsed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
public abstract class _AbstractDto {
  protected ZonedDateTime creationDate;
  protected ZonedDateTime lastModifiedDate;
}
