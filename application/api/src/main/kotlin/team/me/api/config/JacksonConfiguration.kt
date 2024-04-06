package team.me.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import team.me.util.json.GLOBAL_OBJECT_MAPPER

@Configuration
class JacksonConfiguration {
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return GLOBAL_OBJECT_MAPPER
    }
}
