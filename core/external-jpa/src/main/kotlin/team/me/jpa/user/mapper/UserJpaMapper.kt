package team.me.jpa.user.mapper

import org.springframework.stereotype.Component
import team.me.domain.user.domain.DomainUser
import team.me.jpa.user.entity.UserJpaEntity

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@Component
class UserJpaMapper {
    fun registerCommandToJpaEntity(user: DomainUser): UserJpaEntity =
        with(user) {
            UserJpaEntity(0L, email, password!!, nickname, address, isValid, grade.value)
        }

    fun jpaEntityToDomainEntity(userJpaEntity: UserJpaEntity): DomainUser =
        with(userJpaEntity) {
            DomainUser.generateUser(
                id = DomainUser.UserId(0L),
                email = DomainUser.UserEmail(email),
                password = DomainUser.UserPassword(password),
                nickname = DomainUser.UserNickname(nickname),
                address = DomainUser.UserAddress(address),
                isValid = DomainUser.UserIsValid(isValid),
                grade = DomainUser.UserGrade.fromValue(grade),
            )
        }
}
