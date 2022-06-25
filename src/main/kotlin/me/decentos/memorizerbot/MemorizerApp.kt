package me.decentos.memorizerbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MemorizerApp

fun main(args: Array<String>) {
    runApplication<MemorizerApp>(*args)
}