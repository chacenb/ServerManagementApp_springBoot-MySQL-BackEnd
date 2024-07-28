package com.chace.serverManagement.controller;

import com.chace.serverManagement.Model.dto.DataCenterDTO;
import com.chace.serverManagement.Model.dto.PortDTO;
import com.chace.serverManagement.Model.dto.ServerDTO;
import com.chace.serverManagement.Model.entity.Port;
import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.utils.ResponseStructure;
import com.chace.serverManagement.service.ServerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

import static com.chace.serverManagement.Model.enumeration.Status.SERVER_UP;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Slf4j /* Slf4j: Simple Logging Facade for Java : see codeBlocks */
@RestController /* show that class is going to serve rest endpoints api-s, mostly used with @RequestMapping. */
//@RequestMapping(path = "")  /* used to map the web requests */
@RequestMapping(path = "api/v2/server")  /* used to map the web requests */
@RequiredArgsConstructor /* generates constructor for all final & @NonNull fields. Thus handles dependency injection */
public class ServerController {

  /* this wil be injected because of @RequiredArgsConstructor annot. that generates constructor for all final & @NonNull fields */
  private final ServerService serverService;

  /* ResponseEntity<Response> : cf code blocks */
  @GetMapping(path = "/list") // "@GetMapping" is a shortcut for "@RequestMapping(method = RequestMethod.GET)"
  public ResponseEntity<ResponseStructure> getServersList() {
    return ResponseEntity.ok(
        ResponseStructure.builder()
            .timeStamp(ZonedDateTime.now())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Servers fetched successfully !")
            .data(Map.of("servers", serverService.list(30)))
            .build());
  }


  @GetMapping(path = "/get/{id}")
  public ResponseEntity<ResponseStructure> getServer(@PathVariable("id") Long id) {

    ServerDTO serverFetchedByID;
    try {
      serverFetchedByID = serverService.get(id);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
          ResponseStructure.builder()
              .timeStamp(ZonedDateTime.now())
              .status(HttpStatus.BAD_REQUEST)
              .statusCode(HttpStatus.BAD_REQUEST.value())
              .message(e.getMessage())
              .build());

    }

    return ResponseEntity.ok(
        ResponseStructure.builder()
            .timeStamp(ZonedDateTime.now())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Server retrieved successfully")
            .data(Map.of("server", serverFetchedByID))
            .build());
  }


  @GetMapping(path = "/ping/{ipAddress}")
  public ResponseEntity<ResponseStructure> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
    Optional<Server> serv_ = serverService.pingIfExists(ipAddress); /* ping the server and get the result */

    return ResponseEntity.ok(ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .statusCode((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST.value() : HttpStatus.OK.value())
        .status((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST : HttpStatus.OK)

        /* .map(...).orElse(...) just a better way to handle Optional<T> values */
        .message(serv_.map(server -> (server.getStatus() == SERVER_UP ? "Ping success" : "Ping failed")).orElse("No server found with this IP Address"))
        .data((serv_.isEmpty()) ? Map.of() : Map.of("server", serv_.get()))
        .build());

//    return ResponseEntity.ok(ResponseStructure.builder()
//      .timeStamp(ZonedDateTime.now())
//      .statusCode((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST.value() : HttpStatus.OK.value())
//      .status((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST : HttpStatus.OK)
//      .message((serv_.isEmpty()) ? "No server found with this IP Address" : (serv_.get().getStatus() == SERVER_UP ? "Ping success" : "Ping failed"))
//      .data((serv_.isEmpty()) ? Map.of() : Map.of("server", serv_.get()))
//      .build());
  }


  @PutMapping(path = "/{serverId}")
  public ResponseEntity<ResponseStructure> updateServer(@PathVariable("serverId") Long serverId, @RequestBody(required = true /* true is default val */) @Valid ServerDTO serverUpdates) {
    Optional<Server> serv_ = serverService.updateIfExists(serverId, serverUpdates);

    return ResponseEntity.ok(ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .statusCode((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST.value() : HttpStatus.OK.value())
        .status((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST : HttpStatus.OK)
        .data((serv_.isEmpty()) ? Map.of() : Map.of("server", serv_.get()))
        .message((serv_.isEmpty() ? "No server found with this id" : "Server updated"))
        .build());
  }

  @PostMapping(path = "/save")
  public ResponseEntity<ResponseStructure> saveServer(@RequestBody @Valid ServerDTO server) {
    ServerDTO createdSerser = null;
    try {
      createdSerser = serverService.create(server);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
          ResponseStructure.builder()
              .timeStamp(ZonedDateTime.now())
              .status(HttpStatus.BAD_REQUEST)
              .statusCode(HttpStatus.BAD_REQUEST.value())
              .message(e.getMessage())
//              .data((_datacenter.isEmpty()) ? Map.of() : Map.of("dataCenter", _datacenter.get()))
              .build());

    }

    return ResponseEntity.ok(ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .status(HttpStatus.CREATED)
        .statusCode(HttpStatus.CREATED.value())
        .data(Map.of("server", createdSerser))
        .message("Server created")
        .build());
  }

  @PostMapping(path = "/save_v1")
  public ResponseEntity<ResponseStructure> saveServer_v1(@RequestBody @Valid Server server) {
    return ResponseEntity.ok(ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .status(HttpStatus.CREATED)
        .statusCode(HttpStatus.CREATED.value())
        .data(Map.of("server", serverService.old_create(server)))
        .message("Server created")
        .build());
  }


  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<ResponseStructure> deleteServer(@PathVariable("id") Long id) {
    Boolean deleteResult = serverService.delete(id);
    return ResponseEntity.ok(
        ResponseStructure.builder()
            .timeStamp(ZonedDateTime.now())
            .data(Map.of("deleted", deleteResult))
            .message(deleteResult ? "Server deleted successfully" : "No server found with id " + id)
            .statusCode(deleteResult ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
            .status(deleteResult ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
            .build()
    );
  }

  /**
   * api that will handle the setting of a server image.
   * "Produces" means that this handler will NOT return JSON but an IMAGE of the mentioned type
   *
   * @param fileName
   * @return byte[] Returning byte arrays allows us to return almost anything (images or files)
   * @throws IOException because we are accessing filesystem structure
   */
  @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
  public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
    return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/SpringBoot_Projects/images/" + fileName));
  }


  /*
   * When you use the @Valid annotation for a method argument in the Controller,
   * the validator is invoked automatically and it tries to validate the object,
   * if the object is invalid, it throws MethodArgumentNotValidException.
   * If Spring finds a Class/Global level "ExceptionHandler method" for this specific exception, it will execute the code inside this method. */
  @PostMapping(path = "/save/datacenter")
  public ResponseEntity<ResponseStructure> saveDataCenter(@RequestBody @Valid DataCenterDTO dataCenterDTO) {
    log.info("/save/dataCenterDTO body = {}", dataCenterDTO);

    Optional<DataCenterDTO> _datacenter = null;
    try {
      _datacenter = serverService.createDatacenter(dataCenterDTO);
    } catch (Exception e) {
      log.error("serverService.createDatacenter(dataCenterDTO) ::", e);
      return ResponseEntity.badRequest().body(
          ResponseStructure.builder()
              .timeStamp(ZonedDateTime.now())
              .status(HttpStatus.BAD_REQUEST)
              .statusCode(HttpStatus.BAD_REQUEST.value())
              .message(e.getMessage())
//              .data((_datacenter.isEmpty()) ? Map.of() : Map.of("dataCenter", _datacenter.get()))
              .build());

    }
    log.info("_datacenter created  = {}", _datacenter);

    return ResponseEntity.ok(ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .status(HttpStatus.CREATED)
        .statusCode(HttpStatus.CREATED.value())
        .message(_datacenter.map(d_center -> ("dataCenter created")).orElse("No server found with this IP Address"))
        .data((_datacenter.isEmpty()) ? Map.of() : Map.of("dataCenter", _datacenter.get()))
        .build());

  }


  @GetMapping(path = "/get-port/{id}")
  public ResponseEntity<ResponseStructure> getPort(@PathVariable("id") Long id) {

    PortDTO portFetchedByID;
    try {
      portFetchedByID = serverService.getPort(id);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
          ResponseStructure.builder()
              .timeStamp(ZonedDateTime.now())
              .status(HttpStatus.BAD_REQUEST)
              .statusCode(HttpStatus.BAD_REQUEST.value())
              .message(e.getMessage())
              .build());

    }

    return ResponseEntity.ok(
        ResponseStructure.builder()
            .timeStamp(ZonedDateTime.now())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Port retrieved successfully")
            .data(Map.of("port", portFetchedByID))
            .build());
  }


  @PostMapping(path = "/save-port")
  public ResponseEntity<ResponseStructure> savePort(@RequestBody @Valid Port port) {
    Port createdPort = null;
    try {
      createdPort = serverService.createPort(port);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
          ResponseStructure.builder()
              .timeStamp(ZonedDateTime.now())
              .status(HttpStatus.BAD_REQUEST)
              .statusCode(HttpStatus.BAD_REQUEST.value())
              .message(e.getMessage())
              .build());
    }

    return ResponseEntity.ok(ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .status(HttpStatus.CREATED)
        .statusCode(HttpStatus.CREATED.value())
        .data(Map.of("port", createdPort))
        .message("port created")
        .build());
  }


  @GetMapping(path = "/{idServer}/add-port/{idPort}")
  public ResponseEntity<ResponseStructure> addPortToServer(@PathVariable("idServer") Long idServer,
                                                           @PathVariable("idPort") Long idPort) {
    serverService.addPortToServerWithoutCallingSave(idServer, idPort);

    return ResponseEntity.ok(ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .status(HttpStatus.CREATED)
        .statusCode(HttpStatus.CREATED.value())
        .data(Map.of("server", serverService.get(idServer)))
        .message("port created")
        .build());
  }


//
//  @GetMapping(path = "/{idServer}/add-port/{idPort}")
//  public ResponseEntity<ResponseStructure> addPortToServer(@PathVariable("idServer") Long idServer,
//                                                           @PathVariable("idPort") Long idPort) {
//    ServerDTO updatedServer = null;
//    try {
////      updatedServer = serverService.addPortToServer(idServer, idPort);
//      serverService.addPortToServerWithoutCallingSave(idServer, idPort);
//
//    } catch (Exception e) {
//      return ResponseEntity.badRequest().body(
//          ResponseStructure.builder()
//              .timeStamp(ZonedDateTime.now())
//              .status(HttpStatus.BAD_REQUEST)
//              .statusCode(HttpStatus.BAD_REQUEST.value())
//              .message(e.getMessage())
//              .build());
//    }
//
//    return ResponseEntity.ok(ResponseStructure.builder()
//        .timeStamp(ZonedDateTime.now())
//        .status(HttpStatus.CREATED)
//        .statusCode(HttpStatus.CREATED.value())
////        .data(Map.of("server", updatedServer))
//        .data(Map.of("server", serverService.get(idServer)))
//        .message("port created")
//        .build());
//  }


  /** Controller(Class) level exception handling for Validation | cf. FtaExceptionHandler class for global exceptions handling
   * @param notValidException
   * @return
   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException notValidException) {
//
//    List<String> error_details = new ArrayList<>();
//    for (ObjectError error : notValidException.getBindingResult().getAllErrors()) {
//      error_details.add(error.getDefaultMessage());
//    }
//
//    return ResponseEntity.badRequest().body(
//        ResponseStructure.builder()
//            .timeStamp(ZonedDateTime.now())
//            .status(HttpStatus.BAD_REQUEST)
//            .statusCode(HttpStatus.BAD_REQUEST.value())
//            .message(String.valueOf(error_details))
//            .data(null)
//            .build()
//    );
//  }

}
