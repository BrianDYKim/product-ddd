package team.me.v1App.user.mapper

import org.springframework.stereotype.Component
import team.me.domain.user.domain.DomainUser
import team.me.v1App.user.dto.RegisterUserDto

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@Component
class UserResponseMapper {
    fun entityToRegisterResponse(user: DomainUser): RegisterUserDto.Response =
        with(user) {
            RegisterUserDto.Response(id!!, email, nickname, address, isValid, grade.value)
        }
}
