package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.domain.cafe.utils.CafeTestUtils
import org.junit.jupiter.api.Test

internal class CafeTest {
    @Test
    fun create_cafe_test() {
        val cafeRequestDto = CafeTestUtils.createCafeRegisterRequest()

        val cafe = Cafe.createCafe(
            name = cafeRequestDto.name!!,
            address = cafeRequestDto.address!!,
            phoneNumber = cafeRequestDto.phoneNumber!!,
            description = cafeRequestDto.description!!,
            cafeMenuRequests = cafeRequestDto.cafeMenus
        )

        CafeTestUtils.assertCafeEquals(request = cafeRequestDto, entity = cafe)
    }
}