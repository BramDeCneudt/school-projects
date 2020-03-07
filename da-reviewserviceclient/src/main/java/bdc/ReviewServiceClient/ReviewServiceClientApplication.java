package bdc.ReviewServiceClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("bdc.controllers")
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
public class ReviewServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceClientApplication.class, args);
	}
}
