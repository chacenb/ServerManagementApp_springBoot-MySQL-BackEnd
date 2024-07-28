package com.chace.serverManagement.Model.dto;

import com.chace.serverManagement.Model.entity.Dummy;
import com.chace.serverManagement.Model.entity.Port;
import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.PortDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

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
