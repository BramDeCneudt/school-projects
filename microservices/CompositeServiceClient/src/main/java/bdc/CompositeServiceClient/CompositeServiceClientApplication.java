package bdc.CompositeServiceClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("bdc.controllers")
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
public class CompositeServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompositeServiceClientApplication.class, args);
	}
}

