package com.chace.serverManagement.service.implementation;

import com.chace.serverManagement.Model.Server;
import com.chace.serverManagement.enumeration.Status;
import com.chace.serverManagement.repository.ServerRepo;
import com.chace.serverManagement.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

// RequiredArgsConstructor annot. will create a constructor, add the serverRepo property in it
// and that will be our dependency injection
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
  private final ServerRepo serverRepo;

  /* this method is going to be called for each server save, it will:
   * - log the server to save
   * - dynamically set an image to the serv
   * - save the server to the DB and return it */
  @Override
  public Server create(Server server) {
    log.info("[SI] saving new server {}", server.getName());
    server.setImageUrl(setServerImageUrl());
    return serverRepo.save(server);
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
    log.info("[SI] pinging Optional<server> w/ ipAddress : {}", ipAddress);

    if (server.isPresent()) {
      InetAddress address = InetAddress.getByName(ipAddress);
      server.get().setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
      return Optional.of(serverRepo.save(server.get()));
    } else return server;
  }


  @Override
  public Collection<Server> list(int limit) {
    log.info("[SI] fetching all servers ");
//        return serverRepo.findAll(of(0, limit)).toList();
    return serverRepo.findAllByOrderByIdDesc();
  }

  @Override
  public Server get(Long id) {
    log.info("[SI] fetching server w/ id : {}", id);
    return serverRepo.findById(id).get();
  }

  @Override
  public Optional<Server> getOptional(Long id) {
    log.info("[SI] fetching <optional> server w/ id : {}", id);
    return serverRepo.findById(id).isPresent() ? serverRepo.findById(id) : Optional.of(new Server());
  }

  @Override
  @Transactional
  public Server update_old(Long id, Server serverUpdate) {
    Optional<Server> oldServerOptional = serverRepo.findById(id);
    log.info("[SI] old server <Optional> to update is : {}", oldServerOptional);
    if (oldServerOptional.isEmpty()) return new Server();
    else {
      Server oldServer = oldServerOptional.get();
      oldServer.setName(serverUpdate.getName());
      oldServer.setStatus(serverUpdate.getStatus());
      oldServer.setMemory(serverUpdate.getMemory());
      oldServer.setType(serverUpdate.getType());
      oldServer.setIpAddress(serverUpdate.getIpAddress());
      return this.create(oldServer);
    }
  }

  @Override
  @Transactional
  public Optional<Server> updateIfExists(Long id, Server serverUpdate) {
    Optional<Server> oldServerOptional = serverRepo.findById(id);
    log.info("[SI] old server <Optional> to update is : {}", oldServerOptional);
    if (oldServerOptional.isPresent()) {
      Server oldServer = oldServerOptional.get();
      oldServer.setName(serverUpdate.getName());
      oldServer.setStatus(serverUpdate.getStatus());
      oldServer.setMemory(serverUpdate.getMemory());
      oldServer.setType(serverUpdate.getType());
      oldServer.setIpAddress(serverUpdate.getIpAddress());
      return Optional.of(this.create(oldServer));
    } else return oldServerOptional;
  }


  /* STOPPED HERE */
  @Override
  @Transactional
  public Boolean delete(Long id) {
    log.info("[SI] deleting server w/ id : {}", id);
    Optional<Server> serverToDelete = serverRepo.findById(id);
    if (serverToDelete.isEmpty()) return FALSE;
    serverRepo.deleteById(id);
    return TRUE;
  }

  public String setServerImageUrl() {
    String[] imageNames = {"serv0.png", "serv1.png", "serv2.png", "serv3.png", "serv4.png"};
    return ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("api/v2/server/image/" + imageNames[new Random().nextInt(5)])
      .toUriString();
  }
}
