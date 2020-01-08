package microserviceworkshop.movieservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
@EnableDiscoveryClient
class MovieServiceApplication{

}

fun main(args: Array<String>) {
	runApplication<MovieServiceApplication>(*args)
}
