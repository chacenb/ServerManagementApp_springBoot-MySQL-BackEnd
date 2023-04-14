package com.chace.serverManagement.service;

import com.chace.serverManagement.Model.Server;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Optional<Server> getOptional(Long id);
    Server update(Long id, Server server);
    Boolean delete(Long id);



}
