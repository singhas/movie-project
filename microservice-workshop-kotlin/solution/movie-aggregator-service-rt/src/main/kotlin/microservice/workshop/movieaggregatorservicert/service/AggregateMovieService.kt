package microservice.workshop.movieaggregatorservicert.service

import microservice.workshop.movieaggregatorservicert.model.Movie
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class AggregateMovieService(private val movieService: MovieService,
                            private val awardService: MovieAwardService,
                            private val castService: MovieCastService) {

    fun findById(id: Int): Movie? {
        return movieService.findById(id)?.apply {
            addAwards(awardService.findAwardsForMovie(id))
            addCastMembers(castService.findCastMembers(id))
        }
    }
}
