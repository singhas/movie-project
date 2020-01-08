package microserviceworkshop.springbootdemo.http

import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PersonControllerTest(private val webApplicationContext: WebApplicationContext) {
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    @Throws(Exception::class)
    fun testFindAll() {
        mockMvc.perform(get("/person"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize<Any>(4)))
    }

    @Test
    @Throws(Exception::class)
    fun testFindOne() {
        mockMvc.perform(get("/person/1"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", `is`("Fred")))
                .andExpect(jsonPath("$.lastName", `is`("Flintstone")))
    }

    @Test
    @Throws(Exception::class)
    fun testFindNone() {
        mockMvc.perform(get("/person/22"))
                .andExpect(status().`is`(HttpStatus.NOT_FOUND.value()))
    }

    @Test
    @Throws(Exception::class)
    fun testSearch() {
        mockMvc.perform(get("/person/search?lastName=Rubble"))
                .andExpect(status().`is`(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize<Any>(2)))
    }
}