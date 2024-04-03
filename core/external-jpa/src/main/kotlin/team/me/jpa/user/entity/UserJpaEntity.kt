package team.me.jpa.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import team.me.jpa.common.BaseJpaEntity

/**
 * @author Doyeop Kim
 * @since 2024/03/31
 */
@Entity
@Table(name = "users")
open class UserJpaEntity protected constructor() : BaseJpaEntity() {
    @field:Id
    @field:Column(name = "id", nullable = false)
    open var id: Long = 0L

    @field:Column(name = "email", nullable = false)
    open var email: String = ""

    @field:Column(name = "password", nullable = false)
    open var password: String = ""

    @field:Column(name = "nickname", nullable = false)
    open var nickname: String = ""

    @field:Column(name = "address", nullable = false)
    open var address: String = ""

    @field:Column(name = "is_valid", nullable = false)
    open var isValid: Boolean = true

    @field:Column(name = "grade", nullable = false)
    open var grade: String = ""

    constructor(id: Long, email: String, password: String, nickname: String, address: String, isValid: Boolean, grade: String) : this() {
        this.id = id
        this.email = email
        this.password = password
        this.nickname = nickname
        this.address = address
        this.isValid = isValid
        this.grade = grade
    }
}
