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
    dtoServerTypeMap.addMapping(ServerDTO::getServerDetails, Server::setServerDetails);
  }

  public ServerDTO toDTO(Server param) {
    log.info("_____mapping toDTO :: param is = {}", param);
    ServerDTO map = this.modelMapper.map(param, ServerDTO.class);
    log.info("_____mapped OK :: about to return mapped Obj = {}", map);
    return map;
  }

  public Server toEntity(ServerDTO param) {
    log.info("_____mapping toEntity :: param is = {}", param);
    Server map = this.modelMapper.map(param, Server.class);
    log.info("_____mapped OK :: about to return mapped Obj = {}", map);
    return map;
  }

}








