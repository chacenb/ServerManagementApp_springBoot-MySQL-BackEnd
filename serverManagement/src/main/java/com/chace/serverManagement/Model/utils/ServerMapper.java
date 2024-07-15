package com.chace.serverManagement.Model.utils;

import com.chace.serverManagement.Model.dto.ServerDTO;
import com.chace.serverManagement.Model.entity.Server;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Collections;

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

    /* This one uses typeMapping */
    ServerDTO map = this.modelMapper.map(param, ServerDTO.class);
    log.info("toDTO: {}", map);
    return map;

    /* This one is a hand made mapper :: full control on our code */
//    return ServerDTO.builder()
//      ._id(param.getId())
//      .creationDate(param.getCreationDate())
//      .lastModifiedDate(param.getLastModifiedDate())
//      ._ipAddress(param.getIpAddress())
//      .name(param.getName())
//      .memory(param.getMemory())
//      .type(param.getType())
//      .imageUrl(param.getImageUrl())
//      .status(param.getStatus())
//      .serverDetails(param.getServerDetails())
//      .serverDetailsList(Collections.singletonList(param.getServerDetailsList()))
//      .build();
  }

  public Server toEntity(ServerDTO param) {
    Server map = modelMapper.map(param, Server.class);
    log.info("toEntity: {}", map);
    return map;
  }

}








