package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.utils.CafeTestUtils
import org.junit.jupiter.api.Test

internal class CafeTest {
    @Test
    fun create_cafe_test() {
        val cafeRequestDto = CafeTestUtils.createCafeRegisterRequest()

        val cafe = Cafe.createEntity(
            name = cafeRequestDto.name!!,
            address = cafeRequestDto.address!!,
            phoneNumber = cafeRequestDto.phoneNumber!!,
            description = cafeRequestDto.description!!
        )

        CafeTestUtils.assertCafeEquals(request = cafeRequestDto, entity = cafe)
    }
}