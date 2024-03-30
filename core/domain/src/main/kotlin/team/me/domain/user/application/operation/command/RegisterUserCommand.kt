package team.me.domain.user.application.operation.command

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import team.me.architect.validate.SelfValidatingObject

/**
 * @author Doyeop Kim
 * @since 2024/03/30
 */
data class RegisterUserCommand(
    @field:NotNull
    @field:Email
    val email: String,
    @field:NotNull
    @field:Min(8)
    val password: String,
    @field:NotNull
    @field:NotEmpty
    val nickname: String,
    @field:NotNull
    @field:NotEmpty
    val address: String,
) : SelfValidatingObject<RegisterUserCommand>() {
    init {
        this.validateSelf()
    }
}
