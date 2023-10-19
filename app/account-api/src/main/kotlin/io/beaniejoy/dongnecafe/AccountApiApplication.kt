package io.beaniejoy.dongnecafe

import io.beaniejoy.dongnecafe.common.constant.AppModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class AccountApiApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(AccountApiApplication::class.java)
        .properties("spring.config.name=${AppModule.getConfigNames()}")
        .run(*args)
}
