package microserviceworkshop.springbootdemo.data

import microserviceworkshop.springbootdemo.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<Person, Int> {
    fun findByLastName(lastName: String): List<Person>
}