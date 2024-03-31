package team.me.jpa.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@MappedSuperclass
abstract class BaseJpaEntity protected constructor() {
    @field:Column(name = "created_at", nullable = false)
    @field:CreatedDate
    open var createdAt: LocalDateTime = LocalDateTime.now()

    @field:Column(name = "updated_at", nullable = true)
    @field:LastModifiedDate
    open var updatedAt: LocalDateTime? = null

    @field:Column(name = "deleted_at", nullable = true)
    open var deletedAt: LocalDateTime? = null
}
