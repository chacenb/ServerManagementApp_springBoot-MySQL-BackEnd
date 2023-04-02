package com.chace.serverManagement.resource;

import com.chace.serverManagement.Model.Response;
import com.chace.serverManagement.Model.Server;
import com.chace.serverManagement.service.implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.chace.serverManagement.enumeration.Status.SERVER_UP;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController // show that class is going to serve rest endpoints api-s, mostly used with @RequestMapping.
@RequestMapping(path = "api/v2/server")    // used to map the web requests
@RequiredArgsConstructor // generates constructor for all final & @NonNull fields. Thus handle with dependency injection
public class ServerResource {
    //    @Autowired
    private final ServerServiceImplementation serverService;    /* this wil be dependency injected cause of @RequiredArgsConstructor */

    /* "ResponseEntity<T>" Generic type that represents the whole HTTP response: status code, headers, and body.
     * We then use it to fully configure HTTP responses */
//    @CrossOrigin
    @GetMapping(path = "/list") // "@GetMapping" is a shortcut for "@RequestMapping(method = RequestMethod.GET)"

    public ResponseEntity<Response> getAllServers() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message("Servers fetched successfully !")
                .data(Map.of("servers", serverService.list(30)))
                .build());
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message("Server retrieved successfully")
                .data(Map.of("server", serverService.get(id)))
                .build());
    }

    @GetMapping(path = "/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress); /* ping the server and get the result*/
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message(server.getStatus() == SERVER_UP ? "Ping success" : "Ping failed")
                .data(Map.of("server", server))
                .build());
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);

        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(Map.of("server", serverService.create(server)))
                .message("Server created")
                .build());
    }

    @DeleteMapping(path = "/delete/{id}")
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
    /* produces : says that this handler will return NOT A JSON but an IMAGE of the mentioned type */
    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    // Or : public @ResponseBody byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        /* Returning byte arrays allows us to return almost anything (images or files) */
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/SpringBoot_Projects/images/" + fileName));
    }


}
