package io.beaniejoy.dongnecafe

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CommonTest {
    @Test
    @DisplayName("common 테스트")
    fun test() {
        println("hello test")
        assertEquals("hello", "hello")
    }
}