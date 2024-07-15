package com.chace.serverManagement.Model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataCenterDTO {

  /* JAKARTA BEAN VALIDATION :: see all the validation annotations for controllers here
   * https://jakartaee.github.io/jakartaee-documentation/jakartaee-tutorial/current/beanvalidation/bean-validation/bean-validation.html */

  @Column(unique = true)  // creates unique Constraint on this ipAddress Field
  @NotEmpty(message = "data center name can't be empty or null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private String name;

  @AssertTrue(message = "datacenter must be in use to be created")
  private Boolean isInUse;

  @DecimalMax(value = "30", message = "Maximum 30 servers in a datacenter")
  private int numberOfServers;

  private String description;

  private ServerDTO server;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long serverId;

}