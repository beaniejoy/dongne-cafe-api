package io.beaniejoy.dongnecafe.domain.cafe.utils

import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.OptionDetailRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption
import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import javax.persistence.GeneratedValue

class CafeTestUtils {
    companion object {
        fun assertCafeEquals(request: CafeRegisterRequest, entity: Cafe) {
            assertEquals(request.name, entity.name)
            assertEquals(request.address, entity.address)
            assertEquals(request.phoneNumber, entity.phoneNumber)
            assertEquals(request.description, entity.description)

            CafeMenuTestUtils.assertCafeMenuListEquals(request.cafeMenuList, entity.cafeMenuList)
        }

        fun createCafeRegisterRequest(): CafeRegisterRequest {
            val cafeName = "beanie_cafe"
            val cafeAddress = "beanie_cafe_address"
            val phoneNumber = "01012345678"
            val description = "beanie_cafe_description"

            return CafeRegisterRequest(
                name = cafeName,
                address = cafeAddress,
                phoneNumber = phoneNumber,
                description = description,
                cafeMenuList = CafeMenuTestUtils.createCafeMenuRegisterRequestList()
            )
        }

        fun injectCafeId(
            cafe: Cafe,
            newCafeId: Long,
        ): Cafe {
            val idField = cafe.javaClass.declaredFields
                .find { f ->
                    f.getAnnotation(GeneratedValue::class.java) != null
                } ?: return cafe

            idField.isAccessible = true
            idField.set(cafe, newCafeId)

            return cafe
        }
    }
}