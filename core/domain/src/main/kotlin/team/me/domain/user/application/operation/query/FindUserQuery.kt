package team.me.domain.user.application.operation.query

import team.me.architect.validate.SelfValidatingObject

/**
 * @author Doyeop Kim
 * @since 2024/03/30
 */
data class FindUserQuery(
    val id: Long,
) : SelfValidatingObject<FindUserQuery>() {
    init {
        this.validateSelf()
    }
}
