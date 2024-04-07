package team.me.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import team.me.api.config.security.filter.LoginAuthenticationFilter
import team.me.api.config.security.handler.UserAuthenticationSuccessHandler

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val passwordEncoder: PasswordEncoder,
    @Qualifier("userDetailsService") private val userDetailsService: UserDetailsService,
    private val objectMapper: ObjectMapper,
    private val userAuthenticationSuccessHandler: UserAuthenticationSuccessHandler,
) {
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
        val authenticationManager = authenticationManagerBuilder.build()

        http.authenticationManager(authenticationManager)
        val loginFilter = LoginAuthenticationFilter("/api/v1/users/login", authenticationManager, objectMapper)
        loginFilter.setAuthenticationSuccessHandler(userAuthenticationSuccessHandler)

        http.httpBasic {
            it.disable()
        }

        http.formLogin {
            it.disable()
        }

        http.csrf {
            it.disable()
        }

        http.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
            it.requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
            it.anyRequest().authenticated()
        }

        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter::class.java)

        http.headers {
            it.frameOptions {
                it.sameOrigin()
            }
            it.contentSecurityPolicy {
                it.policyDirectives(
                    "script-src 'self'; " + "img-src 'self'; " +
                        "font-src 'self' data:; " + "default-src 'self'; " +
                        "frame-src 'self'",
                )
            }
        }

        return http.build()
    }
}
