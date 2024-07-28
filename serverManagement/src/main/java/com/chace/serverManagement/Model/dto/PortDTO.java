package com.chace.serverManagement.Model.dto;

import com.chace.serverManagement.Model.utils.PortDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
@SuperBuilder
public class PortDTO extends _AbstractDto {
  protected Long        id;
  private   String      name;
  private   PortDetails details;
  private   Object      serverBidir;

}
