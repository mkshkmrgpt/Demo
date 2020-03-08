package com.example.springsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@SpringBootApplication
class SpringSecurityApplication

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http {
            httpBasic {}
            authorizeRequests {
                authorize("*/greetings/**", hasAuthority("ROLE_ADMIN"))
                authorize("/**", permitAll)
            }
        }

    }
}

fun main(args: Array<String>) {
    runApplication<SpringSecurityApplication>(*args) {
        addInitializers(beans {
            bean {
                fun user(user: String, pw: String, vararg roles: String) = User.withDefaultPasswordEncoder().username(user).password(pw).roles(*roles).build()
                InMemoryUserDetailsManager(user("mukesh", "pw", "USER"), user("kumar", "pw1", "ADMIN"))
            }
            bean {
                router {
                    GET("/greetings") { request ->
                        request.principal().map { it.name }
                                .map { ServerResponse.ok().body(mapOf("greetings " to "Hello, $it")) }
                                .orElseGet { ServerResponse.badRequest().build() }
                    }
                }
            }
        })
    }
}
