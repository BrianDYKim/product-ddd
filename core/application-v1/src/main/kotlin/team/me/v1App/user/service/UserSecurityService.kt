package team.me.v1App.user.service

import org.springframework.security.core.userdetails.UserDetails
import team.me.architect.hexagonal.UseCase
import team.me.domain.user.domain.IUserRepository
import team.me.v1App.user.mapper.UserResponseMapper
import team.me.v1App.user.usecase.UserSecurityUseCase

@UseCase(value = "userDetailsService")
class UserSecurityService(
    private val userRepository: IUserRepository,
    private val responseMapper: UserResponseMapper,
) : UserSecurityUseCase {
    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }
}
