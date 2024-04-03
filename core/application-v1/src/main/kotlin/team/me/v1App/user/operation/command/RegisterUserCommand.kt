package team.me.v1App.user.operation.command

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
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
    @field:Length(min = 8)
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
