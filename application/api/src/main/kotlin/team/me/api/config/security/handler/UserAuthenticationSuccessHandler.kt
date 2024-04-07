package team.me.api.config.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import team.me.util.json.toJson
import team.me.v1App.user.dto.LoginUserDto
import team.me.v1App.user.usecase.UserSecurityUseCase
import team.me.webCommon.ResultFactory

@Component
class UserAuthenticationSuccessHandler(
    private val userSecurityService: UserSecurityUseCase,
    private val resultFactory: ResultFactory,
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val userDetails = authentication.principal as UserDetails
        val token = userSecurityService.generateJwtToken(userDetails)

        decorateResponse(response, token)
    }

    private fun decorateResponse(
        response: HttpServletResponse,
        token: String,
    ) {
        val responseDto = LoginUserDto.Response(token)
        val responseBody = resultFactory.getSingleResult(responseDto).toJson()

        response.addHeader("Authorization", "Bearer $token")
        response.contentType = "application/json"
        response.writer.write(responseBody)
        response.writer.flush()
    }
}
