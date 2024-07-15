package com.chace.serverManagement.service.implementation;

import com.chace.serverManagement.Model.dto.ServerDTO;
import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerDetails;
import com.chace.serverManagement.Model.utils.ServerMapper;
import com.chace.serverManagement.repository.ServerRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ServerServiceImplementationTest {

  @Mock private        HttpServletRequest          request;
  @Mock private        ServerRepo                  serverRepo;
  @Mock private        ServerMapper                serverMapper;
  @InjectMocks private ServerServiceImplementation serverService;

  private ServerDTO serverDTO;
  private Server    server;


  @BeforeEach
  void setUp() {
    log.info("________setUp");
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    serverDTO = ServerDTO.builder()
      ._ipAddress("192.168.1.1")
      .name("testServer")
      .status(Status.SERVER_DOWN)
      .memory("64GB")
      .type("")
      .imageUrl("")
      .serverDetails(new ServerDetails())
      .serverDetailsList(new ArrayList<>())
      .build();
    log.info("________setUp :: serverDTO = {}", serverDTO);

    server = Server.builder()
      .ipAddress("192.168.1.1")
      .name("testServer")
      .status(Status.SERVER_DOWN)
      .memory("64GB")
      .type("")
      .imageUrl("")
      .serverDetails(new ServerDetails())
      .serverDetailsList(new ArrayList<>())
      .build();
    log.info("________setUp :: server = {}", server);


  }

  @AfterEach
  void tearDown() {
    log.info("________ tearDown Logic");
  }


  @Test
  @Transactional
  @Rollback
  public void createsServerSuccess() throws Exception {

    // Mock the serverMapper, simulating that the mapping always works
    when(serverMapper.toEntity(any(ServerDTO.class))).thenReturn(server);
    when(serverMapper.toDTO(any(Server.class))).thenReturn(serverDTO);

    /* mock serverRepo.save : simulate that saving to DB always work */
    when(serverRepo.save(any(Server.class))).thenReturn(server);

    // Call the create method
    ServerDTO createdServer = serverService.create(serverDTO);

    // Verify that the save method was called exactly once with the given server
    verify(serverRepo, times(1)).save(server);

    assertNotNull(createdServer);

    /* Assert that the savedServer matches */
    assertEquals(serverDTO.get_ipAddress(), createdServer.get_ipAddress());
    assertEquals(serverDTO.getStatus(), createdServer.getStatus());
    assertEquals(serverDTO.getCreationDate(), createdServer.getCreationDate());

  }

  @Test
  @Transactional
  @Rollback
  public void createsServer_provideNull_throwException() throws Exception {

    assertThrows(Exception.class, () -> serverService.create(null));

    // Verify that the save method was not called
    verify(serverRepo, times(0)).save(server);

  }

  @Test
  @Transactional
  @Rollback
  public void getServer_successful() throws Exception {

    Long serverId = 1L;
    ServerDTO serverDTO1L = ServerDTO.builder()._id(serverId) /* other fields*/.build();

    when(serverRepo.findById(any(Long.class))).thenReturn(Optional.ofNullable(server));
    when(serverMapper.toDTO(any(Server.class))).thenReturn(serverDTO1L);

    ServerDTO gotServer = serverService.get(serverId);

    assertNotNull(gotServer);
    verify(serverRepo, times(1)).findById(any(Long.class));
    assertEquals(serverId, gotServer.get_id());

  }

  @Test
  @Transactional
  @Rollback
  public void getServer_throwsException() throws Exception {

    Long serverId = 1L;
    when(serverRepo.findById(any(Long.class))).thenThrow(new RuntimeException("No server with id" + serverId));

    assertThrows(Exception.class, () -> serverService.get(serverId));

    verify(serverRepo, times(1)).findById(any(Long.class));
  }

}