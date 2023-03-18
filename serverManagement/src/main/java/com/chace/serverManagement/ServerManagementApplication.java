package com.chace.serverManagement;

import com.chace.serverManagement.Model.Server;
import com.chace.serverManagement.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static com.chace.serverManagement.enumeration.Status.SERVER_DOWN;
import static com.chace.serverManagement.enumeration.Status.SERVER_UP;


@SpringBootApplication
//@EnableTransactionManagement
//@ComponentScan("com.chace.serverManagement")
public class ServerManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerManagementApplication.class, args);
    }

    /* STOPPED AT 00:55:55 */

}
