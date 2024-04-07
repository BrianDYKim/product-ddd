package team.me.v1App.user.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import team.me.architect.hexagonal.UseCase
import team.me.domain.user.domain.IUserRepository
import team.me.v1App.component.JwtUtils
import team.me.v1App.error.ErrorLevel
import team.me.v1App.user.exception.UserAuthenticationException
import team.me.v1App.user.mapper.UserResponseMapper
import team.me.v1App.user.usecase.UserSecurityUseCase

@UseCase(value = "userDetailsService")
class UserSecurityService(
    private val userRepository: IUserRepository,
    private val responseMapper: UserResponseMapper,
    private val jwtUtils: JwtUtils,
) : UserSecurityUseCase {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)

        return user?.let {
            User.withUsername("${it.id!!}")
                .password(it.password)
                .build()
        } ?: throw UserAuthenticationException("User not found", "U000", ErrorLevel.INFO)
    }

    override fun generateJwtToken(userDetails: UserDetails): String {
        return jwtUtils.generateToken(userDetails)
    }
}
