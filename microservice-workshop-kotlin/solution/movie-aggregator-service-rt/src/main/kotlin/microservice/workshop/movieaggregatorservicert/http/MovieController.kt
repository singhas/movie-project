package microservice.workshop.movieaggregatorservicert.http

import microservice.workshop.movieaggregatorservicert.model.Movie
import microservice.workshop.movieaggregatorservicert.service.AggregateMovieService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/movie")
class MovieController(private val service: AggregateMovieService) {
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<Movie> {
        println(SecurityContextHolder.getContext().authentication.authorities)
        return service.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }
}
