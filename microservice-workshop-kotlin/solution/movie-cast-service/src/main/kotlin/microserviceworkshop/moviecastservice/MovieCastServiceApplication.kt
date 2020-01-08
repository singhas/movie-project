package microserviceworkshop.moviecastservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
class MovieCastServiceApplication

fun main(args: Array<String>) {
	runApplication<MovieCastServiceApplication>(*args)
}
