package com.chace.serverManagement.Model.dto_notUsed;

import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerDetails;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
public class ServerDTO {

  /* JAKARTA BEAN VALIDATION :: see all the validation annotations for controllers here
   * https://jakartaee.github.io/jakartaee-documentation/jakartaee-tutorial/current/beanvalidation/bean-validation/bean-validation.html */

  @NotEmpty(message = "IP Address can't be empty or null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private String ipAddress;

  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;

  @NotNull(message = "Server details can't be null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private ServerDetails serverDetails;

  @NotEmpty(message = "Server details list can't be empty")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  @NotNull(message = "Server details list can't be  null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private List<ServerDetails> serverDetailsList = new ArrayList<ServerDetails>();


}
