package me.decentos.memorizerbot.controller

import me.decentos.memorizerbot.service.MessageService
import me.decentos.memorizerbot.service.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

@Controller
class BotController(
    private val userService: UserService,
    private val messageService: MessageService,
    @Value("\${bot.token}") private val token: String,
    @Value("\${bot.name}") private val botName: String,
) : TelegramLongPollingBot() {

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = botName

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            val message = update.message
            val chatId = message.chatId.toString()
            val messageText = message.text

            val user = userService.checkUser(message)
            val responseText = messageService.prepareResponse(messageText, user)
            val responseMessage = messageService.sendMessage(chatId, responseText)
            execute(responseMessage)
        }
    }

}