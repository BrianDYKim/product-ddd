package team.me.api.user.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import team.me.v1App.user.dto.LoginUserDto

/**
 * 로그인을 처리하는 필터
 * @see https://velog.io/@kide77/Spring-Boot-3.x-Security-Rest-API-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%9A%94%EC%B2%AD%EB%B6%80-%EA%B5%AC%ED%98%84-1
 * @author Doyeop Kim
 * @since 2024/04/03
 */
class LoginAuthenticationFilter(
    private val defaultFilterProcessUrl: String,
    private val authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper,
) : AbstractAuthenticationProcessingFilter(defaultFilterProcessUrl, authenticationManager) {
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        val requestMethod = request.method

        if (requestMethod != "POST") {
            throw AuthenticationServiceException("Authentication method not supported: $requestMethod")
        }

        val inputStream = request.inputStream
        val loginRequest = objectMapper.readValue(inputStream, LoginUserDto.Request::class.java)

        val authentication =
            with(loginRequest) {
                UsernamePasswordAuthenticationToken(email, password)
            }

        return authenticationManager.authenticate(authentication)
    }
}
