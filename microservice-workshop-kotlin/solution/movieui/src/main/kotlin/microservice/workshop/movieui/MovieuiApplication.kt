package microservice.workshop.movieui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieuiApplication

fun main(args: Array<String>) {
	runApplication<MovieuiApplication>(*args)
}
