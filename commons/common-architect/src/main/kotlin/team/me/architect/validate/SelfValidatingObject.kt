package team.me.architect.validate

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation

/**
 * @author Doyeop Kim
 * @since 2024/03/30
 */
open class SelfValidatingObject<T : Any> {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    protected fun validateSelf() {
        val violations = validator.validate(this)

        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
