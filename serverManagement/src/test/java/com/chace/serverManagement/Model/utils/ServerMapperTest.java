package com.chace.serverManagement.Model.utils;

import com.chace.serverManagement.Model.dto.ServerDTO;
import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.enumeration.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ServerMapperTest {

  private ServerMapper serverMapper;
  private Server       entity;
  private ServerDTO    dto;

  @BeforeAll
  static void beforeAll() {
    log.info("________beforeAll");
  }

  @BeforeEach
  void setUp() {
    serverMapper = new ServerMapper();
  }

  @AfterEach
  void tearDown() {
  }

  @AfterAll
  static void afterAll() {
    log.info("________afterAll");
  }

  @Test
  void shouldMapServerToServerDTO() {

    entity = Server.builder()
      .ipAddress("192.168.1.1")
      .name("testServer")
      .status(Status.SERVER_DOWN)
      .memory("64GB")
      .serverDetails(new ServerDetails())
      .serverDetailsList(new ArrayList<>(Collections.singleton(new ServerDetails())))
      .build();
    log.info("________setUp :: testServer = {}", entity);

    ServerDTO dto = serverMapper.toDTO(entity);

    assertNotNull(dto);

    /* Assert that the savedServer matches */
    assertEquals(dto.get_ipAddress(), entity.getIpAddress());
    assertEquals(dto.getStatus(), entity.getStatus());
    assertEquals(dto.getCreationDate(), entity.getCreationDate());
  }

  @Test
  void toEntity() {
  }
}