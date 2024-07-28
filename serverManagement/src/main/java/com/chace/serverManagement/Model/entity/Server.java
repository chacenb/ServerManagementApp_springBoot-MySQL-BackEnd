package com.chace.serverManagement.Model.entity;

import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerDetails;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Collection;
import java.util.List;
import java.util.Set;
//import org.hibernate.annotations.Type;

@EqualsAndHashCode(callSuper = true) @SuperBuilder
@Entity //(name="t_server") in order to map this student class to the database : used by hibernate
//@Getter @Setter // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
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
public class Server extends _AbstractModel {

  @Column(nullable = false)
  private String ipAddress;

  private String name;
  private String memory;
  private String type;
  private String imageUrl;
  private Status status;

  @JdbcTypeCode(SqlTypes.JSON) /* New feature in hibernate 6 (embedded in springBoot 3) to store Complex types as JSON in DB */
  @Column(name = "server_details", columnDefinition = "JSON")
  private ServerDetails serverDetails;

  @JdbcTypeCode(SqlTypes.JSON) /* New feature in hibernate 6 (embedded in springBoot 3) to store Complex types as JSON in DB */
  @Column(name = "server_details_list", columnDefinition = "JSON")
  private List<ServerDetails> serverDetailsList;

  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<Dummy> dummies;

//  @OneToMany(mappedBy = "server", cascade = CascadeType.PERSIST)
//  private Collection<Port> ports;


  @ManyToMany
  private Collection<Port> ports;



  @Transient// Ignoring Fields With the JPA @Transient Annotation > https://www.baeldung.com/jpa-transient-ignore-field
  private String description;

  private transient String location;

  @Override
  public String toString() {
    return "Server{" + super.toString() +
      "ipAddress='" + ipAddress + '\'' +
      ", name='" + name + '\'' +
      ", memory='" + memory + '\'' +
      ", type='" + type + '\'' +
      ", imageUrl='" + imageUrl + '\'' +
      ", status=" + status +
      ", serverDetails=" + serverDetails +
      ", serverDetailsList=" + serverDetailsList +
      ", description='" + description + '\'' +
      ", location='" + location + '\'' +
      "} " ;
  }
}
