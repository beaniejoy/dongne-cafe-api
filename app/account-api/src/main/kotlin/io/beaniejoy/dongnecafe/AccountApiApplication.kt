package io.beaniejoy.dongnecafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class AccountApiApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(AccountApiApplication::class.java)
        .properties("spring.config.name=application,application-common,application-db")
        .run(*args)
}
