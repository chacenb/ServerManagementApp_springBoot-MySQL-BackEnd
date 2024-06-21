package com.chace.serverManagement.Model.utils;

import com.chace.serverManagement.Model.dto_notUsed.ServerDTO;
import com.chace.serverManagement.Model.entity.Server;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerMapper {

  /* Standard guide to modelMapping using ModelMapper > https://www.baeldung.com/java-modelmapper */
  private final ModelMapper modelMapper = new ModelMapper();

  // #1- When there is a difference between entity and DTO, configure the fields mapping
  TypeMap<Server, ServerDTO> serverDtoTypeMap = this.modelMapper.createTypeMap(Server.class, ServerDTO.class);
  TypeMap<ServerDTO, Server> dtoServerTypeMap = this.modelMapper.createTypeMap(ServerDTO.class, Server.class);

  @PostConstruct
  public void initializeTypeMaps() {

    // #2 - And add the different mappings
    serverDtoTypeMap.addMapping(Server::getId, ServerDTO::set_id);
    serverDtoTypeMap.addMapping(Server::getIpAddress, ServerDTO::set_ipAddress);
    /*------------*/
    dtoServerTypeMap.addMapping(ServerDTO::get_id, Server::setId);
    dtoServerTypeMap.addMapping(ServerDTO::get_ipAddress, Server::setIpAddress);
  }

  public ServerDTO toDTO(Server server) {
    return this.modelMapper.map(server, ServerDTO.class);
  }

  public Server toEntity(ServerDTO serverDTO) {
    return this.modelMapper.map(serverDTO, Server.class);
  }

  public ServerDTO toDTO2(Server server) {
    return this.modelMapper.map(server, ServerDTO.class);
  }

  public Server toEntity2(ServerDTO serverDTO) {return this.modelMapper.map(serverDTO, Server.class);}


}








