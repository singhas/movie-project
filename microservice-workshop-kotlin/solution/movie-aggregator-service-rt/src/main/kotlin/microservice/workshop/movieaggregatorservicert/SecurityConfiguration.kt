package microservice.workshop.movieaggregatorservicert

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
//    override fun
//            configure(http: HttpSecurity) {
//        http.authorizeRequests()
//            .anyRequest().permitAll()
//            .and()
//            .httpBasic().disable()
//            .csrf().disable()
//        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
//    }
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("discUser")
            .password("{noop}discPassword").roles("SYSTEM")
        auth.inMemoryAuthentication().withUser("adminA").password("{noop}adminA").roles("ADMIN")
    }
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/").hasAnyRole("USER", "ADMIN", "SYSTEM")
            .antMatchers("/movie/**").hasAnyRole("USER", "ADMIN", "SYSTEM")
            .and().httpBasic().and().csrf().disable()
    }
}
