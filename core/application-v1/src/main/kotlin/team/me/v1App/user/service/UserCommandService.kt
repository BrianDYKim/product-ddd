package team.me.v1App.user.service

import org.springframework.security.crypto.password.PasswordEncoder
import team.me.architect.hexagonal.UseCase
import team.me.domain.user.domain.DomainUser
import team.me.domain.user.domain.IUserRepository
import team.me.v1App.user.dto.RegisterUserDto
import team.me.v1App.user.mapper.UserResponseMapper
import team.me.v1App.user.operation.command.RegisterUserCommand
import team.me.v1App.user.usecase.UserCommandUseCase

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@UseCase
class UserCommandService(
    private val userRepository: IUserRepository,
    private val responseMapper: UserResponseMapper,
    private val passwordEncoder: PasswordEncoder,
) : UserCommandUseCase {
    override fun store(command: RegisterUserCommand): RegisterUserDto.Response {
        val encryptedCommand = this.encryptCommand(command)

        val user =
            DomainUser.generateUser(
                id = DomainUser.UserId(0L),
                email = DomainUser.UserEmail(encryptedCommand.email),
                password = DomainUser.UserPassword(encryptedCommand.password),
                nickname = DomainUser.UserNickname(encryptedCommand.nickname),
                address = DomainUser.UserAddress(encryptedCommand.address),
                isValid = DomainUser.UserIsValid(true),
                grade = DomainUser.UserGrade.BRONZE,
            )

        val storedUser = userRepository.store(user)

        return responseMapper.entityToRegisterResponse(storedUser)
    }

    private fun encryptCommand(command: RegisterUserCommand): RegisterUserCommand =
        with(command) {
            val newPassword = passwordEncoder.encode(password)
            RegisterUserCommand(email, newPassword, nickname, address)
        }
}
