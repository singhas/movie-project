package microservice.workshop.movieawardservice

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer


@EnableRedisHttpSession
class SessionConfig : AbstractHttpSessionApplicationInitializer()
