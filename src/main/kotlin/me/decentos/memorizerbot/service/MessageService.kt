package me.decentos.memorizerbot.service

import me.decentos.memorizerbot.entity.dto.UserDto
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Service
class MessageService {

    fun prepareResponse(messageText: String, userDto: UserDto): String {
        return when {
            messageText == "/start" -> "Welcome"
            messageText.startsWith("Button ") -> "Push $messageText"
            else -> "Text: *$messageText* from ${userDto.chatId}"
        }
    }

    fun sendMessage(chatId: String, responseText: String): SendMessage {
        val message = SendMessage(chatId, responseText)
        message.enableMarkdownV2(true)
        message.replyMarkup = getReplyMarkup(
            listOf(
                listOf("Button 1", "Button 2"),
                listOf("Button 3", "Button 4")
            )
        )
        return message
    }

    private fun getReplyMarkup(allButtons: List<List<String>>): ReplyKeyboardMarkup {
        val markup = ReplyKeyboardMarkup()
        markup.keyboard = allButtons.map { rowButtons ->
            val row = KeyboardRow()
            rowButtons.forEach { rowButton -> row.add(rowButton) }
            row
        }
        return markup
    }
}