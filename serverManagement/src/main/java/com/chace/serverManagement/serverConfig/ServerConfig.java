package com.chace.serverManagement.serverConfig;

import com.chace.serverManagement.Model.Server;
import com.chace.serverManagement.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.chace.serverManagement.enumeration.Status.SERVER_DOWN;
import static com.chace.serverManagement.enumeration.Status.SERVER_UP;

@Configuration
public class ServerConfig {
    @Bean
    CommandLineRunner startupConfig(ServerRepo serverRepo) {
        return args -> {
            Server serv1 = new Server(null, "192.168.1.150", "Ubuntu Server", "32 GB", "Personal PC", "http://localhoat:8080/api/v2/server/image/serv0.png", SERVER_UP);
            Server serv2 = new Server(null, "192.168.2.113", "Microsoft Server 2019", "64 GB", "CISCO", "http://localhoat:8080/api/v2/server/image/serv1.png", SERVER_DOWN);
            Server serv3 = new Server(null, "192.168.1.39", "Debian", "16 GB", "Remote", "http://localhoat:8080/api/v2/server/image/serv2.png", SERVER_DOWN);
            serverRepo.saveAll(List.of(serv1, serv2, serv3));
        };
    }
}
