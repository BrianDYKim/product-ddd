package team.me.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import team.me.webCommon.ResultFactory

/**
 * @author Doyeop Kim
 * @since 2024/04/03
 */
@Configuration
class ApiConfiguration {
    @Bean
    fun resultFactory(): ResultFactory {
        return ResultFactory()
    }
}
