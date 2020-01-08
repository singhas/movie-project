package com.example.gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableRedisHttpSession(
        flushMode = FlushMode.IMMEDIATE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("In configureGlobal");
        auth.inMemoryAuthentication().withUser("user").password("{noop}password")
                .roles("USER").and().withUser("admin").password("{noop}admin")
                .roles("ADMIN").and().withUser("other").password("{noop}other").roles("OTHER");
        System.out.println("Out of configureGlobal");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("In configure");
//        http.authorizeRequests().antMatchers("/movie-aggregator-service-rt")
//                .permitAll().antMatchers("/eureka/**").hasRole("ADMIN")
//                .anyRequest().authenticated().and().formLogin().and()
//                .logout().permitAll().logoutSuccessUrl("/movie-aggregator-service-rt")
//                .permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/movie-aggregator-service-rt/**")
                .hasAnyRole("USER", "ADMIN").antMatchers("/eureka/**").hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().and()
                .logout().permitAll().logoutSuccessUrl("/movie-aggregator-service-rt")
                .permitAll().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        System.out.println("Out of configure");
    }
}