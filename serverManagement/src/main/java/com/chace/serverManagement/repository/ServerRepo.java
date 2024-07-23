package com.chace.serverManagement.repository;

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
public interface ServerRepo extends JpaRepository<Server, Long> {


  /**
   * custom find all (that is not deleted)
   * @param specification
   * @return
   */
  List<Server> findAll(Specification<Server> specification);

  /**
   * This method will extend JpaRepository:
   * The ipAddress is unique, and we want to search by it.
   * Just by the name format of the method, it will be translated into a JPA Query
   *
   * @param ipAddress
   * @return Optional<Server>
   */
  Optional<Server> findByIpAddress(String ipAddress);
//    Server findByIpAddress(String ipAddress);


  /**
   * Custom find all servers and order by Id Descending
   *
   * @return a List of Servers
   */
  List<Server> findAllByOrderByIdDesc();

  @Modifying
  @Query("UPDATE Server s SET s.isNotdeleted = false, s.deletedDate = :deleteDate, s.deletedBy=:deleterId WHERE s.id = :serverId")
  public void deleteById(@Param("serverId") Long serverId, @Param("deleterId") Long userId, @Param("deleteDate") ZonedDateTime deleteDate);





  /* list all the specifications here to construct queries with criterias  */

  static Specification<Server> isNotDeleted() {
    return (T, cq, cb) -> cb.equal(T.get("isNotdeleted"), true);
  }

  static Specification<Server> loginEquals(String login) {
    return (T, cq, cb) -> cb.equal(T.get("login"), login);
  }
}
