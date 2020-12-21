package fr.gantoin.raspbeflix.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan
@EnableScheduling
@SpringBootApplication
public class RaspbetflixBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaspbetflixBackendApplication.class, args);
    }

}
