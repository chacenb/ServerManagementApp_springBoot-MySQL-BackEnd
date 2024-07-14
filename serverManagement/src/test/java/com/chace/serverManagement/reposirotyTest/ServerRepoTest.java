package com.chace.serverManagement.reposirotyTest;

import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerDetails;
import com.chace.serverManagement.repository.ServerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ServerRepoTest {

  @Autowired private ServerRepo serverRepo;
  private            Server     testServer;

  @BeforeEach /* Execute some actions before each test method */
  public void setUp() {
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
    serverRepo.delete(testServer);  // Release test data after each test method
  }

  @Test
  void givenServer_whenSaved_thenCanBeFoundById() {
    Server savedServer = serverRepo.getReferenceById(testServer.getId()); // serverRepo.findBy(testServer.getId()).orElse(null);
    assertNotNull(savedServer);
    assertEquals(testServer.getIpAddress(), savedServer.getIpAddress());
    assertEquals(testServer.getStatus(), savedServer.getStatus());
  }

  @Test
  void givenServer_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
    testServer.setName("updatedServerName");
    serverRepo.save(testServer);

    Server updatedServer = serverRepo.findById(testServer.getId()).orElse(null);

    assertNotNull(updatedServer);
    assertEquals("updatedServerName", updatedServer.getName());
  }

}
