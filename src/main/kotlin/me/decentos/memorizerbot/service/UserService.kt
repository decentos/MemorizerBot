package me.decentos.memorizerbot.service

import me.decentos.memorizerbot.entity.dto.UserDto
import me.decentos.memorizerbot.entity.model.User
import me.decentos.memorizerbot.repository.UserRepository
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun checkUser(message: Message): UserDto {
        return try {
            getUser(message.chatId.toString())
        } catch (e: Exception) {
            saveUser(message)
        }
    }

    fun getUser(chatId: String): UserDto {
        val user = userRepository.findByChatId(chatId).orElseThrow()
        return UserDto(
            id = user.id,
            chatId = user.chatId,
            firstName = user.firstName,
            lastName = user.lastName,
            userName = user.userName,
            currentLevel = user.currentLevel,
            created = user.created,
            updated = user.updated,
        )
    }

    fun saveUser(message: Message): UserDto {
        val savedUser = userRepository.save(
            User(
                id = UUID.randomUUID().toString(),
                chatId = message.chatId.toString(),
                firstName = message.from.firstName,
                lastName = message.from.lastName,
                userName = message.from.userName,
                currentLevel = 1,
            )
        )
        return UserDto(
            id = savedUser.id,
            chatId = savedUser.chatId,
            firstName = savedUser.firstName,
            lastName = savedUser.lastName,
            userName = savedUser.userName,
            currentLevel = savedUser.currentLevel,
            created = savedUser.created,
            updated = savedUser.updated,
        )
    }

}