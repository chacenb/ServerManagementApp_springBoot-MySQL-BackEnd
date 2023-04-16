package com.chace.serverManagement.repository;

import com.chace.serverManagement.Model.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* a repository interface class should ALWAYS extend JpaRepository
* and specify the Class we want to manage and the type of PrimaryKEY
* by extending JpaRepository we have access to all the methods to manipulate the DataBase
* */
@Repository
public interface ServerRepo extends JpaRepository<Server, Long> {

    /**
     * This method will extend JpaRepository:
     * The ipAddress is unique, and we want to search by it.
     * Just by the name format of the method, it will be translated into a JPA Query
     * @param ipAddress
     * @return Optional<Server>
     */
    Optional<Server> findByIpAddress(String ipAddress);
//    Server findByIpAddress(String ipAddress);


    /**
     * Custom find all servers and order by Id Descending
     * @return a List of Servers
     */
    List<Server> findAllByOrderByIdDesc();


}
