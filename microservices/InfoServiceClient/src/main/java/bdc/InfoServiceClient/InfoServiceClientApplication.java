package bdc.InfoServiceClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan("bdc.controllers")
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
public class InfoServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoServiceClientApplication.class, args);
	}
}


