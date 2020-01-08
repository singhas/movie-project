package microserviceworkshop.movieservice.http

import microserviceworkshop.movieservice.data.MovieRepository
import microserviceworkshop.movieservice.model.Movie
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RefreshScope
@RequestMapping("/movie")
class MovieController(private val repository: MovieRepository) {
    @Value("\${test.config}")
    var welcomeText: String? = null

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): ResponseEntity<Movie> {
        println(welcomeText)
        return ResponseEntity.of(repository.findById(id))
    }

    //@Autowired
    //lateinit var configuration: Configuration
}
