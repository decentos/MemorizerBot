package me.decentos.memorizerbot.repository

import me.decentos.memorizerbot.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, String> {

    fun findByChatId(chatId: String): Optional<User>
}