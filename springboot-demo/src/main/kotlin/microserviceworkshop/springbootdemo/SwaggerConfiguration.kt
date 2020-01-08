package microserviceworkshop.springbootdemo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import springfox.documentation.swagger2.annotations.EnableSwagger2
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import org.springframework.web.servlet.view.RedirectView
import org.springframework.web.bind.annotation.RequestMapping

@Configuration
@EnableSwagger2
@Controller
class SwaggerConfiguration {
    @RequestMapping("/")
    fun redirectToSwagger()= RedirectView("swagger-ui.html")

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
                .build()
    }
}