package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;
import java.util.logging.Level;


@RestController
@SpringBootApplication
//@RequestMapping("/demo")
//@EnableDiscoveryClient
public class DemoApplication {

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOG = Logger.getLogger(DemoApplication.class.getName());

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/first")
	public String available() {
		LOG.log(Level.INFO, "In first service - Working on port " + env.getProperty("local.server.port"));
		return restTemplate.getForObject("http://localhost:8888/second", String.class);
		//return "Spring in Action";
	}

	@RequestMapping(value = "/second")
	public String checkedOut() {
		LOG.log(Level.INFO, "In second service");
		return "Spring Boot in Action";
	}

	@RequestMapping(value = "/demo")
	public String demo() {
		LOG.log(Level.INFO, "In demo service");
		return restTemplate.getForObject("http://localhost:8888/first", String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
