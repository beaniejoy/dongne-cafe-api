package io.beaniejoy.dongnecafe

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    properties = ["spring.config.name=application,application-common,application-db"]
)
internal class ServiceApiApplicationTests {

    @Test
    fun contextLoads() {
    }
}
