package com.chace.serverManagement.Model.utils;

import com.chace.serverManagement.Model.dto.PortDTO;
import com.chace.serverManagement.Model.dto.ServerDTO;
import com.chace.serverManagement.Model.dto._AbstractDto;
import com.chace.serverManagement.Model.entity.Port;
import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.entity._AbstractModel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

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

  public ServerDTO toDTO(Server entity) {

    //    ServerDTO map = this.modelMapper.map(entity, ServerDTO.class);  /* This one uses typeMapping */
    ServerDTO map = setCommonFields(
        entity, ServerDTO.builder()
            ._ipAddress(entity.getIpAddress())
            .name(entity.getName())
            .memory(entity.getMemory())
            .type(entity.getType())
            .imageUrl(entity.getImageUrl())
            .status(entity.getStatus())
            .serverDetails(entity.getServerDetails())
            .serverDetailsList(Arrays.asList(entity.getServerDetailsList().toArray()))
            .portsUnidir(Objects.nonNull(entity.getPortsUnidir()) ? entity.getPortsUnidir().stream().map(_AbstractModel::getId).collect(Collectors.toSet()) : new ArrayList<>())
            .portsBidir(Objects.nonNull(entity.getPortsBidir()) ? entity.getPortsBidir().stream().map(_AbstractModel::getId).collect(Collectors.toSet()) : new ArrayList<>())
            .description(entity.getDescription())
            .build()
    );


    log.info("toDTO: {}", map);
    return map;
  }

  public PortDTO portToDTO(Port entity) {
//    PortDTO map = this.modelMapper.map(entity, PortDTO.class);

    /* Or use  the method below :: WORKS TOO */
    PortDTO map = setCommonFields(entity, PortDTO.builder()
        .name(entity.getName())
        .details(entity.getDetails())
        .serverBidir(Objects.isNull(entity.getServerBidir()) ? null : entity.getServerBidir().getId())
        .build());

    log.info("portToDTO: {}", map);
    return map;
  }

  public Server toEntity(ServerDTO param) {
    Server map = modelMapper.map(param, Server.class);
    log.info("toEntity: {}", map);
    return map;
  }

  private static <E extends _AbstractModel, D extends _AbstractDto> D setCommonFields(E entity, D dto) {
    dto.set_id(entity.getId());
    dto.setCreationDate(entity.getCreationDate());
    dto.setLastModifiedDate(entity.getLastModifiedDate());
    dto.setDeletedDate(entity.getDeletedDate());
    dto.setIsNotDeleted(entity.getIsNotDeleted());
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setModifiedBy(entity.getModifiedBy());
    dto.setDeletedBy(entity.getDeletedBy());
    return dto;
  }

}








