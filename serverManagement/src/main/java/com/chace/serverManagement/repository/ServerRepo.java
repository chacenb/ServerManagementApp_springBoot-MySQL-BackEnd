package com.chace.serverManagement.repository;

import com.chace.serverManagement.Model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* a repository interface class should ALWAYS extend JpaRepository
* and specify the Class we want to manage and the type of PrimaryKEY
* by extending JpaRepository we have access to all the methods to manipulate the DataBase
* */
@Repository
public interface ServerRepo extends JpaRepository<Server, Long> {

    /* Method that will extend JpaRepository:
    * The ipAddress is unique, and we want to search by it
    * Just by the name format of the method, it will be translated into a JPA Query
    * thus extend JpaRepository */
    Server findByIpAddress(String ipAddress);

}
