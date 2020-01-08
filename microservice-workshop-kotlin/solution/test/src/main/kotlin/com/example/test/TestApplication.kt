package com.example.test

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
class TestApplication{
	@Value("\${welcome.message}")
	var welcomeText: String? = null
}

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
	println("In Test App")
}
