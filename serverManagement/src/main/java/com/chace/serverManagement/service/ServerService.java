package com.chace.serverManagement.service;

import com.chace.serverManagement.Model.dto_notUsed.DataCenterDTO;
import com.chace.serverManagement.Model.dto_notUsed.ServerDTO;
import com.chace.serverManagement.Model.entity.Server;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface ServerService {
  ServerDTO create(ServerDTO server) throws Exception;

  Server old_create(Server server);

  Optional<DataCenterDTO> createDatacenter(DataCenterDTO dataCenterDTO) throws Exception;

  Server ping_old(String ipAddress) throws IOException;

  Optional<Server> pingIfExists(String ipAddress) throws IOException;

  Collection<Server> list(int limit);

  Server get(Long id);

  Optional<Server> getOptional(Long id);

  Server update_old(Long id, Server server);

  Optional<Server> updateIfExists(Long id, Server serverUpdate);

  Boolean delete(Long id);
}
