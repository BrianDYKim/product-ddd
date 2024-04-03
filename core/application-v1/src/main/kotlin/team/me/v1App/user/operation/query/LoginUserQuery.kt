package team.me.v1App.user.operation.query

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import team.me.architect.validate.SelfValidatingObject

/**
 * @author Doyeop Kim
 * @since 2024/04/03
 */
data class LoginUserQuery(
    @field:Email
    @field:NotEmpty
    val email: String,
    @field:NotNull
    @field:NotEmpty
    val rawPassword: String,
) : SelfValidatingObject<LoginUserQuery>()
