package microservice.workshop.movieaggregatorservice.service

import org.springframework.web.bind.annotation.RequestParam
import microservice.workshop.movieaggregatorservice.model.CastMember
import org.springframework.web.bind.annotation.GetMapping
import microservice.workshop.movieaggregatorservice.service.fallback.MovieCastServiceFallback
import org.springframework.cloud.openfeign.FeignClient


@FeignClient(name = "movie-cast-service", fallback = MovieCastServiceFallback::class)
interface MovieCastService {

    @GetMapping("/cast/search")
    fun findCastMembers(@RequestParam("movieId") movieId: Int): List<CastMember>
}