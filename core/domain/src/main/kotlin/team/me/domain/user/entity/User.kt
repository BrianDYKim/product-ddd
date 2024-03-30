package team.me.domain.user.entity

/**
 * @author Doyeop Kim
 * @since 2024/03/30
 */
class User private constructor(
    val id: Long,
    val email: String,
    val password: String?,
    val nickname: String,
    val address: String,
    val isValid: Boolean,
) {
    companion object {
        fun generateUser(
            id: UserId,
            email: UserEmail,
            password: UserPassword,
            nickname: UserNickname,
            address: UserAddress,
            isValid: UserIsValid,
        ): User {
            return User(id.value, email.value, password.value, nickname.value, address.value, isValid.value)
        }
    }

    data class UserId(val value: Long)

    data class UserEmail(val value: String)

    data class UserPassword(val value: String?)

    data class UserNickname(val value: String)

    data class UserAddress(val value: String)

    data class UserIsValid(val value: Boolean)
}
