package com.chace.serverManagement.configurations;

import com.chace.serverManagement.Model.entity.Server;
import com.chace.serverManagement.Model.utils.ServerDetails;
import com.chace.serverManagement.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static com.chace.serverManagement.Model.enumeration.Status.SERVER_DOWN;
import static com.chace.serverManagement.Model.enumeration.Status.SERVER_UP;


/* This class is used to initialize some values inside the DB*/
@Configuration
public class ServerConfig {
  @Bean
  CommandLineRunner startupConfig(ServerRepo serverRepo) {
    return args -> {
//      Server server0 = Server.builder()
//                             .name("name")
//                             .ipAddress("0.0.0.0")
//                             .memory("32 GB")
//                             .type("Personal PC")
//                             .status(SERVER_DOWN)
//                             .serverDetails(new ServerDetails())
//                             .serverDetailsList(new ArrayList<>())
//                             .description("description")
//                             .location("location")
//                             .build();
//
//      serverRepo.saveAll(List.of(server0));
    };
  }
}
