package com.chace.serverManagement.controller;

import com.chace.serverManagement.Model.utils.ResponseStructure;
import com.chace.serverManagement.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Map;

@Slf4j /* Slf4j: Simple Logging Facade for Java : see codeBlocks */
@RestController /* show that class is going to serve rest endpoints api-s, mostly used with @RequestMapping. */
//@RequestMapping(path = "")  /* used to map the web requests */
@RequestMapping(path = "api/v2/server/public")  /* used to map the web requests */
@RequiredArgsConstructor /* generates constructor for all final & @NonNull fields. Thus handles dependency injection */
public class PublicServerController {

  /* this wil be injected because of @RequiredArgsConstructor annot. that generates constructor for all final & @NonNull fields */
  private final ServerService serverService;

  /* ResponseEntity<Response> : cf code blocks */
  @GetMapping(path = "") // "@GetMapping" is a shortcut for "@RequestMapping(method = RequestMethod.GET)"
  public ResponseEntity<ResponseStructure> getAllServers() {
    return ResponseEntity.ok(
      ResponseStructure.builder()
        .timeStamp(ZonedDateTime.now())
        .statusCode(HttpStatus.OK.value())
        .status(HttpStatus.OK)
        .message("Servers fetched successfully !")
        .data(Map.of("servers", serverService.allServers_withoutAuthentication()))
        .build());
  }

}
