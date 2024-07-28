package com.chace.serverManagement.repository;

import com.chace.serverManagement.Model.entity.Port;
import com.chace.serverManagement.Model.entity.Port_;
import com.chace.serverManagement.Model.entity.Server;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/* a repository interface class should ALWAYS extend JpaRepository
 * and specify the Class we want to manage and the type of PrimaryKEY
 * by extending JpaRepository we have access to all the methods to manipulate the DataBase
 * */
@Repository
public interface PortRepo extends JpaRepository<Port, Long> {

  Port findByName(String name);


  /* list all the specifications here to construct queries with criterias  */

  static Specification<Port> isNotDeleted() {
    return (T, cq, cb) -> cb.isNull(T.get(Port_.IS_NOTDELETED));
  }

}
