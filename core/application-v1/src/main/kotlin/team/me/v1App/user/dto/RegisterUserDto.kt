package team.me.v1App.user.dto

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
sealed class RegisterUserDto {
    data class Request(
        val email: String,
        val password: String,
        val nickname: String,
        val address: String,
    )

    data class Response(
        val id: Long,
        val email: String,
        val nickname: String,
        val address: String,
        val isValid: Boolean,
        val grade: String,
    )
}
