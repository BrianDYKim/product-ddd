package team.me.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
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
            it.requestMatchers(HttpMethod.POST, "/api/v1/users/").permitAll()
            it.anyRequest().permitAll()
        }

        return http.build()
    }
}
