package me.decentos.memorizerbot.entity

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    var id: String,

    @Column(name = "chat_id", nullable = false)
    var chatId: String,

    @Column(name = "current_level", nullable = false)
    var currentLevel: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User
        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , chatId = $chatId , currentLevel = $currentLevel )"
    }
}
