package team.me.v1App.user.usecase

import team.me.v1App.user.dto.RegisterUserDto
import team.me.v1App.user.operation.command.RegisterUserCommand

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
interface UserCommandUseCase {
    /**
     * @param command RegisterUserCommand
     * @return responseDto RegisterUserDto.Response
     */
    fun store(command: RegisterUserCommand): RegisterUserDto.Response
}
