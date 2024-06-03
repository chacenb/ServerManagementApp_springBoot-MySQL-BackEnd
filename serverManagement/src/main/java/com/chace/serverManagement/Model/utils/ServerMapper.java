package com.chace.serverManagement.Model.utils;

import com.chace.serverManagement.Model.dto_notUsed.ServerDTO;
import com.chace.serverManagement.Model.entity.Server;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ServerMapper {


  /* Standard guide to modelMapping using ModelMapper > https://www.baeldung.com/java-modelmapper */
  private final ModelMapper modelMapper = new ModelMapper();

  public ServerDTO toDTO(Server server) {
    return this.modelMapper.map(server, ServerDTO.class);
  }

  public Server toEntity(ServerDTO serverDTO) {
    return this.modelMapper.map(serverDTO, Server.class);
  }


}

