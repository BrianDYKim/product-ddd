package team.me.domain.user.domain

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
interface IUserRepository {
    fun store(user: DomainUser): DomainUser
}
