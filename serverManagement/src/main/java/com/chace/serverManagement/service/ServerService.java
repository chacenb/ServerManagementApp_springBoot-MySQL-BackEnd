package com.chace.serverManagement.service;

import com.chace.serverManagement.Model.dto.DataCenterDTO;
import com.chace.serverManagement.Model.dto.ServerDTO;
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

  Collection<Server> all();

  Collection<Server> list(int limit);

  ServerDTO get(Long id) throws Exception;

  Optional<Server> getOptional(Long id);

  Server update_old(Long id, Server server);

  Optional<Server> updateIfExists(Long id, ServerDTO serverUpdate);

  Boolean delete(Long id);
}
