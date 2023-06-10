//package io.beaniejoy.dongnecafe.domain.cafe.entity
//
//import io.beaniejoy.dongnecafe.cafe.entity.Cafe
//import io.beaniejoy.dongnecafe.utils.CafeTestUtils
//import org.junit.jupiter.api.Disabled
//import org.junit.jupiter.api.Test
//
//internal class CafeTest {
//    @Disabled
//    @Test
//    fun create_cafe_test() {
//        val cafeRequestDto = CafeTestUtils.createCafeRegisterRequest()
//
//        val cafe = Cafe.createEntity(
//            name = cafeRequestDto.name!!,
//            address = cafeRequestDto.address!!,
//            phoneNumber = cafeRequestDto.phoneNumber!!,
//            description = cafeRequestDto.description!!
//        )
//
//        CafeTestUtils.assertCafeEquals(request = cafeRequestDto, entity = cafe)
//    }
//}