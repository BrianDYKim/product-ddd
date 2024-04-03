package team.me.jpa.user.repository

import team.me.architect.hexagonal.PersistenceAdapter
import team.me.domain.user.domain.DomainUser
import team.me.domain.user.domain.IUserRepository
import team.me.jpa.user.mapper.UserJpaMapper

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@PersistenceAdapter
class UserRepository(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserJpaMapper,
) : IUserRepository {
    override fun store(user: DomainUser): DomainUser {
        val userJpaEntity = userMapper.registerCommandToJpaEntity(user)

        val storedUserJpaEntity = userJpaRepository.save(userJpaEntity)

        return userMapper.jpaEntityToDomainEntity(storedUserJpaEntity)
    }
}
