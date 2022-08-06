package io.beaniejoy.dongnecafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DongneCafeApiApplication

fun main(args: Array<String>) {
    runApplication<DongneCafeApiApplication>(*args)
}