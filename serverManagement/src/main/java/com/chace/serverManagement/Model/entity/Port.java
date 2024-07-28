package com.chace.serverManagement.Model.entity;

import com.chace.serverManagement.Model.utils.PortDetails;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@EqualsAndHashCode(callSuper = true) @Data @AllArgsConstructor @NoArgsConstructor @Builder @Entity
@Table(name = "t_port")
public class Port extends _AbstractModel {

  private String name;

  @JdbcTypeCode(SqlTypes.JSON) /* New feature in hibernate 6 (embedded in springBoot 3) to store Complex types as JSON in DB */
  @Column(columnDefinition = "JSON")
  private PortDetails details;

  @ManyToOne
  @JoinColumn(name = "id_server", foreignKey = @ForeignKey(name = "FK_PORT_SERVER"))
  private Server serverBidir;

}