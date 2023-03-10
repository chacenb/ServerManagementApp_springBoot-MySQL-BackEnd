package com.chace.serverManagement.resource;

import com.chace.serverManagement.Model.Response;
import com.chace.serverManagement.Model.Server;
import com.chace.serverManagement.enumeration.Status;
import com.chace.serverManagement.service.implementation.ServerServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.chace.serverManagement.enumeration.Status.SERVER_UP;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController // show that class is going to serve rest endpoints api-s, mostly used with @RequestMapping.
@RequestMapping(path = "api/v2/server")    // used to map the web requests
@RequiredArgsConstructor // generates constructor for all final & @NonNull fields. Thus handle with dependency injection
public class ServerResource {

    private final ServerServiceImplementation serverService;    /* this wil be dependency injected cause of @RequiredArgsConstructor */

    /* "ResponseEntity<T>" Generic type that represents the whole HTTP response: status code, headers, and body.
    * We then use it to fully configure HTTP responses */
    @GetMapping(path="/list")
    public ResponseEntity<Response> getAllServers() {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message("Servers fetched successfully !")
                .data(Map.of("Servers", serverService.list(30)))
                .build());
    }

    @GetMapping(path="/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress); /* ping the server and get the result*/
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message( server.getStatus() == SERVER_UP ? "Ping success" : "Ping failed")
                .data(Map.of("Server", server))
                .build());
    }

    @PostMapping(path="/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(Map.of("Server", serverService.create(server)))
                .message("Server created")
                .build());
    }

    @GetMapping(path="/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message("Server retrieved successfully")
                .data(Map.of("Server", serverService.get(id)))
                .build());
    }


    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of("deleted", serverService.delete(id)))
                .message("Server deleted successfully")
                .statusCode(OK.value())
                .status(OK)
                .build());
    }

    /* api that will handle the setting of a server image */
    /* produces : says that this handler will return NOT A JSON but an IMAGE */
    @GetMapping(path="/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "SpringBoot_Projects\\images" + fileName));
    }


}
