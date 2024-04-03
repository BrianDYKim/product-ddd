package team.me.v1App.user.usecase

import team.me.v1App.user.dto.LoginUserDto
import team.me.v1App.user.operation.query.LoginUserQuery

/**
 * @author Doyeop Kim
 * @since 2024/03/30
 */
interface UserQueryUseCase {
    fun login(query: LoginUserQuery): LoginUserDto.Response
}
