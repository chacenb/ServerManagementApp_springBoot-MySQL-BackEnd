package com.chace.serverManagement.resource;

import com.chace.serverManagement.Model.Response;
import com.chace.serverManagement.Model.Server;
import com.chace.serverManagement.service.implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static com.chace.serverManagement.enumeration.Status.SERVER_UP;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Slf4j /* Slf4j: Simple Logging Facade for Java : see codeBlocks */
@RestController /* show that class is going to serve rest endpoints api-s, mostly used with @RequestMapping. */
@RequestMapping(path = "api/v2/server")  /* used to map the web requests */
@RequiredArgsConstructor /* generates constructor for all final & @NonNull fields. Thus handles dependency injection */
public class ServerResource {

  /* this wil be dependency injected cause of @RequiredArgsConstructor */
  private final ServerServiceImplementation serverService;

  /* ResponseEntity<Response> : cf code blocks */
  @GetMapping(path = "/list") // "@GetMapping" is a shortcut for "@RequestMapping(method = RequestMethod.GET)"
  public ResponseEntity<Response> getAllServers() {
    return ResponseEntity.ok(
      Response.builder()
        .timeStamp(LocalDateTime.now())
        .statusCode(HttpStatus.OK.value())
        .status(HttpStatus.OK)
        .message("Servers fetched successfully !")
        .data(Map.of("servers", serverService.list(30)))
        .build());
  }


  @GetMapping(path = "/get/{id}")
  public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
    Server serverFetchedByID = serverService.get(id);
    return ResponseEntity.ok(
      Response.builder()
        .timeStamp(LocalDateTime.now())
        .statusCode(HttpStatus.OK.value())
        .status(HttpStatus.OK)
        .message("Server retrieved successfully")
        .data(Map.of("server", serverFetchedByID))
        .build());
  }


  @GetMapping(path = "/ping/{ipAddress}")
  public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
    Optional<Server> serv_ = serverService.pingIfExists(ipAddress); /* ping the server and get the result */

    return ResponseEntity.ok(Response.builder()
      .timeStamp(LocalDateTime.now())
      .statusCode((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST.value() : HttpStatus.OK.value())
      .status((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST : HttpStatus.OK)
      .message((serv_.isEmpty()) ? "No server found with this IP Address" : (serv_.get().getStatus() == SERVER_UP ? "Ping success" : "Ping failed"))
      .data((serv_.isEmpty()) ? Map.of() : Map.of("server", serv_.get()))
      .build());
  }


  @PutMapping(path = "/{serverId}")
  public ResponseEntity<Response> updateServer(@PathVariable("serverId") Long serverId, @RequestBody(required = true) @Valid Server serverUpdates) {
    Optional<Server> serv_ = serverService.updateIfExists(serverId, serverUpdates);

    return ResponseEntity.ok(Response.builder()
      .timeStamp(LocalDateTime.now())
      .statusCode((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST.value() : HttpStatus.OK.value())
      .status((serv_.isEmpty()) ? HttpStatus.BAD_REQUEST : HttpStatus.OK)
      .data((serv_.isEmpty()) ? Map.of() : Map.of("server", serv_.get()))
      .message((serv_.isEmpty() ? "No server found with this id" : "Server updated"))
      .build());
  }

  @PostMapping(path = "/save")
  public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
    return ResponseEntity.ok(Response.builder()
      .timeStamp(LocalDateTime.now())
      .status(HttpStatus.CREATED)
      .statusCode(HttpStatus.CREATED.value())
      .data(Map.of("server", serverService.create(server)))
      .message("Server created")
      .build());
  }


  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
    Boolean deleteResult = serverService.delete(id);
    return ResponseEntity.ok(Response.builder()
      .timeStamp(LocalDateTime.now())
      .data(Map.of("deleted", deleteResult))
      .message(deleteResult ? "Server deleted successfully" : "No server found with id " + id)
      .statusCode(deleteResult ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
      .status(deleteResult ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
      .build());
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


}
