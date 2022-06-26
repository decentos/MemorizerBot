package me.decentos.memorizerbot.entity.dto

import java.time.LocalDateTime

data class UserDto(
    val id: String,
    val chatId: String,
    val firstName: String?,
    val lastName: String?,
    val userName: String?,
    val currentLevel: Int,
    val created: LocalDateTime,
    val updated: LocalDateTime,
)
