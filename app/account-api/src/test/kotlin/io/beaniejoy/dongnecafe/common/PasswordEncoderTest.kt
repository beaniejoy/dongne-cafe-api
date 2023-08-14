package io.beaniejoy.dongnecafe.common

import mu.KLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class PasswordEncoderTest {
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    companion object : KLogging()

    @Test
    fun makeEncodedPasswordTest() {
        val encodedPw = passwordEncoder.encode("1111")
        assertEquals("{bcrypt}\$2a$10\$Wb71jZO9Z.1HXozEnmtqdOHSxCJjn9jMcVWNzqCgd7SW.nZj/1kC2", encodedPw)
    }
}
