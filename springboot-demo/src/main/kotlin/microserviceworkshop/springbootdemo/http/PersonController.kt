package microserviceworkshop.springbootdemo.http

import microserviceworkshop.springbootdemo.data.PersonRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(private val repository: PersonRepository) {

    @GetMapping
    fun findAll() =
            ResponseEntity.ok(repository.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int) =
            ResponseEntity.of(repository.findById(id))

    @GetMapping("/search")
    fun search(@RequestParam("lastName") lastName: String) =
            ResponseEntity.ok(repository.findByLastName(lastName))
}