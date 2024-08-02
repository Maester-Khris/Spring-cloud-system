package nk.projects.spbmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyServiceApplication.class, args);
	}

}
