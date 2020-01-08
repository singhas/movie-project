package microservice.workshop.movieaggregatorservicert


import org.springframework.context.annotation.Configuration
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer


@EnableRedisHttpSession
@Configuration
class SessionConfig : AbstractHttpSessionApplicationInitializer()