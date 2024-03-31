package team.me.v1App.user.operation.query

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
