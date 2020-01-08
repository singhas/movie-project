package microservice.workshop.eurekaserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.PrintWriter
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
//@EnableWebSecurity
@Order(1)
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("discUser")
            .password("{noop}discPassword").roles("SYSTEM")
        auth.inMemoryAuthentication().withUser("adminE").password("{noop}adminE").roles("ADMIN")
    }

    @Autowired
    val authenticationEntryPoint: MyBasicAuthenticationEntryPoint? = null

    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .and().requestMatchers().antMatchers("/")
            .and().authorizeRequests().antMatchers("/")
            .hasRole("ADMIN").and().requestMatchers().antMatchers("/**")
            .and().authorizeRequests().antMatchers("/**").hasAnyRole("SYSTEM", "ADMIN")
            .anyRequest().denyAll().and()
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().csrf().disable()
        println("In SecurityConfig")
    }
}

@Component
class MyBasicAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {

    override fun commence(
        request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException) {
        response.addHeader("WWW-Authenticate", "Basic realm=$realmName")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer: PrintWriter = response.writer
        writer.println("HTTP Status 401 - " + authEx.message)
        println("This is in commence")
        println(request.getHeader("origin"))
        println(authEx.message)
    }

    override fun afterPropertiesSet(){
        realmName = "Test Realm Name";
        super.afterPropertiesSet();
    }
}

////@Configuration
//class AdminSecurityConfig :
//    WebSecurityConfigurerAdapter() {
//    override fun configure(http: HttpSecurity) {
//        http.sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//            .and().httpBasic().disable().authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/").hasRole("ADMIN")
//            .antMatchers("/info", "/health").authenticated().anyRequest()
//            .denyAll().and().csrf().disable()
//    }
//}
//@Configuration
//class SecurityConfig : WebSecurityConfigurerAdapter() {
//    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//            .anyRequest().permitAll()
//            .and()
//            .httpBasic().disable()
//            .csrf().disable()
//    }
//}
