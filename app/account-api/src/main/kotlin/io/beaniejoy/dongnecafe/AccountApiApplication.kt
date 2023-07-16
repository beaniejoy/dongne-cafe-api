package io.beaniejoy.dongnecafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccountApiApplication

fun main(args: Array<String>) {
    runApplication<AccountApiApplication>(*args)
}