package team.me.v1App.user.dto

/**
 * @author Doyeop Kim
 * @since 2024/04/03
 */
sealed class LoginUserDto {
    data class Request(
        val email: String,
        val password: String,
    )

    data class Response(
        val token: String,
    )
}
