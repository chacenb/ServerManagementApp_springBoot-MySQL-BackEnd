package com.chace.serverManagement.Model.entity;

import com.chace.serverManagement.Model.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //(name="t_server") in order to map this student class to the database : used by hibernate
@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
//@Table( //This annot is used to specify the primary table for the annotated entity */
//    name = "server",
//
//    /* defining a custom unique constraint on a table column */
//    uniqueConstraints = {
//        @UniqueConstraint(name = "CONSTR_server_ipAddress_unique", columnNames = "ip_address"),
//    }
//)
public class Server {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)  // creates unique Constraint on this ipAddress Field
  @NotEmpty(message = "IP Address can't be empty or null") // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private String ipAddress;
  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;
}
