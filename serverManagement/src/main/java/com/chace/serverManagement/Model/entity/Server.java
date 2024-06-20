package com.chace.serverManagement.Model.entity;

import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
public class Server extends AbstractModel{

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

  // or use the new springboot 3 way w/ hibernate 6
//  @JdbcTypeCode(SqlTypes.JSON) /* New feature in hibernate 6 (embedded in springBoot 3) to store Complex types as JSON in DB */
//  @Column(name="server_details", columnDefinition = "JSON")
//  private ServerDetails serverDetails;

//  @Type(JsonType.class)
//  @JdbcTypeCode(SqlTypes.JSON) /* New feature in hibernate 6 (embedded in springBoot 3) to store Complex types as JSON in DB */
//  @Column(name="server_details_list", columnDefinition = "JSON")
//  private List<ServerDetails> serverDetailsList = new ArrayList<ServerDetails>();

  // Ignoring Fields With the JPA @Transient Annotation > https://www.baeldung.com/jpa-transient-ignore-field
  @Transient
  private String description;

  private transient String location;
}
