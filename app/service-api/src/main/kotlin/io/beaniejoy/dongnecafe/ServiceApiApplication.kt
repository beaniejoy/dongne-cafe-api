package io.beaniejoy.dongnecafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceApiApplication

fun main(args: Array<String>) {
    runApplication<ServiceApiApplication>(*args)
}