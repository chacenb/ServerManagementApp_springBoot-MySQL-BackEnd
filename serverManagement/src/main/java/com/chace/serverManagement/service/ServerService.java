package com.chace.serverManagement.service;

import com.chace.serverManagement.Model.dto.DataCenterDTO;
import com.chace.serverManagement.Model.dto.PortDTO;
import com.chace.serverManagement.Model.dto.ServerDTO;
import com.chace.serverManagement.Model.entity.Port;
import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.enumeration.Status;
import com.chace.serverManagement.Model.utils.ServerMapper;
import com.chace.serverManagement.configurations.securityConfiguration.UserPrincipal;
import com.chace.serverManagement.exception.CustomException;
import com.chace.serverManagement.repository.PortRepo;
import com.chace.serverManagement.repository.ServerRepo;
import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.chace.serverManagement.Model.utils.Utils.pagingSorting;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor @Service @Transactional @Slf4j
public class ServerService implements com.chace.serverManagement.service.interfaces.IServerService {

  private final ServerRepo   serverRepo;
  private final PortRepo     portRepo;
  private final ServerMapper serverMapper;

  /* this method is going to be called for each serverDTO save, it will:
   * - log the serverDTO to save
   * - dynamically set an image to the serv
   * - save the serverDTO to the DB and return it */
  @Override
  public ServerDTO create(ServerDTO serverDTO)/* throws Exception*/ {
    log.info("[SI] creating new serverDTO {} of class = {}", serverDTO.getName(), serverDTO.getClass().getName());
    serverDTO.setImageUrl(setServerImageUrl());

    /* convert from DTO to Entity before save */
    Server server = serverMapper.toEntity(serverDTO);

    /* setting other Entity fields */
    server.setDescription("default description");
    server.setLocation("IAI");

    /* END setting other Entity fields */
    log.info("about to save = {}", server);

    Server created_server = null;
//    try {
    created_server = serverRepo.save(server);
//    } catch (Exception e) {
//      log.error("ERROR ON SAVE", e);
//      throw new Exception("ERROR ON SAVE :: " + e.getMessage(), e);
//    }
    log.info("saved server = {}", created_server);

    return serverMapper.toDTO(created_server);
  }

  @Override
  public Server old_create(Server server) {
    log.info("[SI] creating new server {}", server.getName());
    server.setImageUrl(setServerImageUrl());
    return serverRepo.save(server);
  }

  @Override
  public Optional<DataCenterDTO> createDatacenter(DataCenterDTO dataCenterDTO) {
    log.info("creating Datacenter dataCenterDTO = {}", dataCenterDTO);

    /* check if the server associated exists */
    Optional<Server> server = serverRepo.findById(dataCenterDTO.getServerId());
    log.info("server.isPresent() = {}", server.isPresent());
    if (server.isEmpty()) throw new RuntimeException("The mentionned server id does not exist! Please provide an existing server");

    /* Save the */

    return Optional.of(new DataCenterDTO());
  }


  @Override
  public Server ping_old(String ipAddress) throws IOException {
//        log.info("[SI] pinging server w/ ip : {}", ipAddress);
//        Server server = serverRepo.findByIpAddress(ipAddress);
//        InetAddress address = InetAddress.getByName(ipAddress);
//        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
//        serverRepo.save(server);
//        return server;
    return new Server();
  }

  @Override
  public Optional<Server> pingIfExists(String ipAddress) throws IOException {
    Optional<Server> server = serverRepo.findByIpAddress(ipAddress);
    log.info("[SI] pinging [ if exists ] server w/ ipAddress : {}", ipAddress);

    if (server.isPresent()) {
      InetAddress address = InetAddress.getByName(ipAddress);
      server.get().setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
      log.info("[SI] server {} pinged successfully within timeout", ipAddress);
      return Optional.of(serverRepo.save(server.get()));
    } else {
      log.info("[SI] server {} ping timed out", ipAddress);
      return server;
    }
  }


  @Override
  public Collection<Server> allServers_withoutAuthentication() {
    log.info("[SI] fetching all servers ");

    if (SecurityContextHolder.getContext().getAuthentication() == null) throw new RuntimeException("User Not authenticated");

    // returns AnonymousAuthenticationToken [Principal=anonymousUser, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[ROLE_ANONYMOUS]]
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    log.info("currPrincpl = {}, Credentials = {}, Authenticated = {}, Details = {}", auth.getPrincipal(), auth.getCredentials(), auth.isAuthenticated(), auth.getDetails());

    return serverRepo.findAll(); // or :: serverRepo.findAllByOrderByIdDesc();
  }


  @Override
  public Collection<ServerDTO> serversList(int pageIndex, int pageSize) {
    log.info("[SI] fetching all servers ");
    return serverRepo.findAll(ServerRepo.isNotDeleted(), pagingSorting()).stream().map(serverMapper::toDTO).collect(Collectors.toSet()); // or :: serverRepo.findAllByOrderByIdDesc();
  }

  @Override
  public Collection<PortDTO> portsList(int pageIndex, int pageSize) {
    log.info("[SI] fetching all servers ");
    return portRepo.findAll(PortRepo.isNotDeleted(), pagingSorting()).stream().map(serverMapper::portToDTO).collect(Collectors.toSet()); // or :: serverRepo.findAllByOrderByIdDesc();
  }

  @Override
  public ServerDTO get(Long id) {
    log.info("[SI] fetching server w/ id : {}", id);
    Server theServer = serverRepo.findById(id).orElseThrow(() -> new CustomException("No server with id =[" + id + "]"));
    return serverMapper.toDTO(theServer);
  }

  @Override
  public Optional<Server> getOptional(Long id) {
    log.info("[SI] fetching [ if exists ] server w/ id : {}", id);
    return serverRepo.findById(id);
  }

  @Override
  @Transactional
  public Server update_old(Long id, Server serverUpdate) {
    Optional<Server> oldServerOptional = serverRepo.findById(id);
    log.info("[SI] old server [ if exists ] to update is : {}", oldServerOptional);
    if (oldServerOptional.isEmpty()) {
      log.info("[SI] server w/ id : {} doesn't exist", id);
      return new Server();
    } else {
      Server oldServer = oldServerOptional.get();
      oldServer.setName(serverUpdate.getName());
      oldServer.setStatus(serverUpdate.getStatus());
      oldServer.setMemory(serverUpdate.getMemory());
      oldServer.setType(serverUpdate.getType());
      oldServer.setIpAddress(serverUpdate.getIpAddress());
      log.info("[SI] server w/ id : {} updated successfully => {}", id, oldServer);
      return this.old_create(oldServer);
    }
  }

  @Override
  @Transactional
  public Optional<Server> updateIfExists(Long id, ServerDTO serverUpdate) {
    Optional<Server> oldServerOptional = serverRepo.findById(id);
    log.info("[SI] old server [ if exists ] to update is : {}", oldServerOptional);
    if (oldServerOptional.isPresent()) {
      Server oldServer = oldServerOptional.get();
      oldServer.setName(serverUpdate.getName());
      oldServer.setStatus(serverUpdate.getStatus());
      oldServer.setMemory(serverUpdate.getMemory());
      oldServer.setType(serverUpdate.getType());
      oldServer.setIpAddress(serverUpdate.get_ipAddress());
//      return Optional.of(this.old_create(oldServer));
      return Optional.of(serverRepo.save(oldServer));
    } else {
      log.info("[SI] server w/ id : {} doesn't exist", id);
      return oldServerOptional;
    }
  }


  /* STOPPED HERE */
  @Override
  @Transactional
  public Boolean delete(Long serverId) {
    log.info("[SI] deleting server w/ serverId : {}", serverId);
    Optional<Server> serverToDelete = serverRepo.findById(serverId);
    if (serverToDelete.isEmpty()) {
      log.info("[SI] server w/ serverId : {} doesn't exist", serverId);
      return FALSE;
    }


    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserPrincipal currPrincpl = (UserPrincipal) authentication.getPrincipal();

      serverRepo.deleteById(serverId, currPrincpl.getUserId(), ZonedDateTime.now());
      log.info("[SI] server w/ serverId : {} deleted successfully", serverId);
      return TRUE;
    }
    return FALSE;
  }

  public String setServerImageUrl() {
    String[] imageNames = {"serv0.png", "serv1.png", "serv2.png", "serv3.png", "serv4.png"};
    return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("api/v2/server/image/" + imageNames[new Random().nextInt(5)])
        .toUriString();
  }


  @Override
  public Port createPort(Port port) {
    return portRepo.save(port);
  }


  @Override
  public PortDTO getPort(Long id) throws RuntimeException {
    log.info("[SI] fetching port w/ id : {}", id);

    Port thePort = Optional.ofNullable(id).map(this.portRepo::getReferenceById).orElseThrow(() -> new RuntimeException("Server with id [" + id + "] not found"));
    log.info("[SI] port : {}", thePort);

    return serverMapper.portToDTO(thePort);
  }

  @Override
  public void addPortToServerWithoutCallingSave_usingUnidirestionalRelationship(Long idServer, Long idPort) {
    Server server = serverRepo.findById(idServer).orElseThrow(() -> new CustomException("No server with id =[" + idServer + "]"));
    Port port = portRepo.findById(idPort).orElseThrow(() -> new CustomException("No port with id =[" + idPort + "]"));
    server.getPortsUnidir().add(port);
  }

  @Override
  public ServerDTO addPortToServer_usingBidirectionalRelationship(Long idServer, Long idPort) {
    Server server = serverRepo.findById(idServer).orElseThrow(() -> new CustomException("No server with id =[" + idServer + "]"));
    Port port = portRepo.findById(idPort).orElseThrow(() -> new CustomException("No port with id =[" + idPort + "]"));
    server.getPortsBidir().add(port);

    /* port side actions */
    port.setServerBidir(server);

    /* for bidirectional relationship, we have to call the save method on the server | Even cascade the saving to the Port Entity */
    return serverMapper.toDTO(serverRepo.save(server));
  }

}
