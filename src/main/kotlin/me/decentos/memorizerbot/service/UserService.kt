package me.decentos.memorizerbot.service

import me.decentos.memorizerbot.entity.User
import me.decentos.memorizerbot.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUser(chatId: String): User {
        val user = userRepository.findByChatId(chatId)
        return user.orElseThrow()
    }

    fun saveUser(chatId: String): User {
        return userRepository.save(
            User(
                id = UUID.randomUUID().toString(),
                chatId = chatId,
                currentLevel = 1
            )
        )
    }

}