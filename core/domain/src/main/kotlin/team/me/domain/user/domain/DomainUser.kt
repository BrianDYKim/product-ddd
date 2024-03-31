package team.me.domain.user.domain

/**
 * @author Doyeop Kim
 * @since 2024/03/30
 */
class DomainUser private constructor(
    val id: Long,
    val email: String,
    val password: String?,
    val nickname: String,
    val address: String,
    val isValid: Boolean,
    val grade: UserGrade,
) {
    companion object {
        fun generateUser(
            id: UserId,
            email: UserEmail,
            password: UserPassword,
            nickname: UserNickname,
            address: UserAddress,
            isValid: UserIsValid,
            grade: UserGrade,
        ): DomainUser {
            return DomainUser(id.value, email.value, password.value, nickname.value, address.value, isValid.value, grade)
        }
    }

    data class UserId(val value: Long)

    data class UserEmail(val value: String)

    data class UserPassword(val value: String?)

    data class UserNickname(val value: String)

    data class UserAddress(val value: String)

    data class UserIsValid(val value: Boolean)

    enum class UserGrade(val value: String) {
        BRONZE("BRONZE"),
        SILVER("SILVER"),
        GOLD("GOLD"),
        PLATINUM("PLATINUM"),
        DIAMOND("DIAMOND"),
        ;

        companion object {
            fun fromValue(value: String): UserGrade {
                return entries.find { it.value == value }
                    ?: throw IllegalArgumentException("Invalid grade value: $value")
            }
        }
    }
}
