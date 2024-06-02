package com.chace.serverManagement.Model.dto_notUsed;

import com.chace.serverManagement.Model.enumeration.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
public class ServerDTO {

  @Column(unique = true)  // creates unique Constraint on this ipAddress Field
  @NotEmpty(message = "IP Address can't be empty or null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private String ipAddress;
  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;

}
