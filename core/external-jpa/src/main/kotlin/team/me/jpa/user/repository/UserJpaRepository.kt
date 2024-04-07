package team.me.jpa.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.me.jpa.user.entity.UserJpaEntity

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@Repository
interface UserJpaRepository : JpaRepository<UserJpaEntity, Long> {
    fun findByNickname(nickname: String): UserJpaEntity?

    fun findByEmail(email: String): UserJpaEntity?
}
