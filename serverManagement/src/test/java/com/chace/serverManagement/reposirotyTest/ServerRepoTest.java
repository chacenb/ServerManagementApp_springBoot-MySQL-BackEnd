package com.chace.serverManagement.reposirotyTest;

import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerDetails;
import com.chace.serverManagement.repository.ServerRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@DataJpaTest
public class ServerRepoTest {

  @Autowired
  private ServerRepo serverRepo;
  private Server     testServer;

  @BeforeEach /* Execute some actions before each test method */
  public void setUp() {
    log.info("________Setting up test");
    // Initialize test data before each test method
    testServer = Server.builder()
      .ipAddress("0.0.0.0")
      .name("serverName")
      .status(Status.SERVER_DOWN)
      .memory("64GB")
      .serverDetails(new ServerDetails())
      .serverDetailsList(new ArrayList<>(Collections.singleton(new ServerDetails())))
      .build();

    serverRepo.save(testServer);
  }

  @AfterEach  /* Execute some actions after each test method */
  public void tearDown() {
    log.info("________ Setting up test");
    serverRepo.delete(testServer);  // Release test data after each test method
  }

  @Test
  @Transactional
  @Rollback
  void givenServer_whenSaved_thenCanBeFoundById() {
    Server savedServer = serverRepo.getReferenceById(testServer.getId()); // serverRepo.findBy(testServer.getId()).orElse(null);

    /* Assert that the retrieved user is not null */
    assertNotNull(savedServer);

    /* Assert that the savedServer matches */
    assertEquals(testServer.getIpAddress(), savedServer.getIpAddress());
    assertEquals(testServer.getStatus(), savedServer.getStatus());
  }

  @Test
  @Transactional
  @Rollback
  void givenServer_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
    testServer.setName("updatedServerName");
    serverRepo.save(testServer);

    Server updatedServer = serverRepo.findById(testServer.getId()).orElse(null);

    assertNotNull(updatedServer);
    assertEquals("updatedServerName", updatedServer.getName());
  }

}
