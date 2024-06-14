package com.chace.serverManagement.Model.entity;

import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerDetails;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
//import org.hibernate.annotations.Type;


@Entity //(name="t_server") in order to map this student class to the database : used by hibernate
@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
@Table( //This annot is used to specify the primary table for the annotated entity */
    name = "server",

    /* define uniqueness of a column | Can also define it on the column directly, but we wouldn't be able to customize the name */
    uniqueConstraints = {
        @UniqueConstraint(name = "CONSTR_UNIQUE_server_ipAddress", columnNames = "ipAddress"),
    }
)
public class Server {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

//  @Column(nullable = false, unique = true)  // Can also unique constraint here, but we wouldn't be able to customize the name */
  @Column(nullable = false)
  private String ipAddress;

  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;

  //  store JSON (Collection of) Object in JPA Entity using JPA and Hibernate 6 (dependencies required) >  https://vladmihalcea.com/how-to-map-json-collections-using-jpa-and-hibernate/
  @Type(JsonType.class)
  @Column(name="server_details", columnDefinition = "json")
  private ServerDetails serverDetails;

  @Type(JsonType.class)
  @Column(name="server_details_list", columnDefinition = "json")
  private List<ServerDetails> serverDetailsList = new ArrayList<ServerDetails>();

  // Ignoring Fields With the JPA @Transient Annotation > https://www.baeldung.com/jpa-transient-ignore-field
  @Transient
  private String description;

  private transient String location;
}
