package team.me.v1App.user.service

import team.me.architect.hexagonal.UseCase
import team.me.v1App.user.dto.LoginUserDto
import team.me.v1App.user.operation.query.LoginUserQuery
import team.me.v1App.user.usecase.UserQueryUseCase

/**
 * @author Doyeop Kim
 * @since 2024/03/30
 */
@UseCase
class UserQueryService : UserQueryUseCase {
    override fun login(query: LoginUserQuery): LoginUserDto.Response {
        TODO("Not yet implemented")
    }
}
