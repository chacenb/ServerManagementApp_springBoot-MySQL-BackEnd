package com.chace.serverManagement.Model.entity;

import com.chace.serverManagement.Model.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity //(name="t_server") in order to map this student class to the database : used by hibernate
@Data   // helps autoInsert getters & setters
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)  // creates unique Constraint on this ipAddress Field
    @NotEmpty(message = "IP Address can't be empty or null") // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;
}
