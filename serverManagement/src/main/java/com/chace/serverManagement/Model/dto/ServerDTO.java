package com.chace.serverManagement.Model.dto;

import com.chace.serverManagement.Model.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
@SuperBuilder
public class ServerDTO extends _AbstractDto {

  /* JAKARTA BEAN VALIDATION :: see all the validation annotations for controllers here
   * https://jakartaee.github.io/jakartaee-documentation/jakartaee-tutorial/current/beanvalidation/bean-validation/bean-validation.html */

  //  @NotEmpty(message = "IP Address can't be empty or null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  @NotBlank(message = "_ipAddress field can't be empty or null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private String _ipAddress;

  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;

  @NotNull(message = "Server details can't be null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private Object serverDetails; //  private ServerDetails serverDetails;

  @NotEmpty(message = "Server details list can't be empty")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  @NotNull(message = "Server details list can't be  null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private List<Object> serverDetailsList; // List<ServerDetails> serverDetailsList;

  @JsonIgnore
  private String description;

  @JsonIgnore
  private transient String location;

  @Override
  public String toString() {
    return "ServerDTO{" + super.toString() +
      "_ipAddress='" + _ipAddress + '\'' +
      ", name='" + name + '\'' +
      ", memory='" + memory + '\'' +
      ", type='" + type + '\'' +
      ", imageUrl='" + imageUrl + '\'' +
      ", status=" + status +
      ", serverDetails=" + serverDetails +
      ", serverDetailsList=" + serverDetailsList +
      ", description='" + description + '\'' +
      ", location='" + location + '\'' +
      "} ";
  }
}
