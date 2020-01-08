package microserviceworkshop.moviecastservice.http

import microserviceworkshop.moviecastservice.data.CastMemberRepository
import microserviceworkshop.moviecastservice.model.CastMember
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cast")
class CastController(private val repository: CastMemberRepository) {

    @Value("\${eureka.instance.instance-id}")
    var instanceId: String? = null

    @GetMapping("/search")
    fun search(@RequestParam movieId: Int): ResponseEntity<List<CastMember>> {
        println("Service $instanceId invoked")
        return ResponseEntity.ok(repository.findByMovieId(movieId))
    }
}
