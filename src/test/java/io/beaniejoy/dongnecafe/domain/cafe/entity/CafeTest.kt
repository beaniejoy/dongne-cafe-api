package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeMenuInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.MenuOptionInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.OptionDetailInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.utils.CafeTestUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class CafeTest {
    @Test
    fun create_cafe_test() {
        val cafeRequestDto = createCreateCafeRequestDto()

        val cafe = Cafe.createCafe(
            name = cafeRequestDto.name!!,
            address = cafeRequestDto.address!!,
            phoneNumber = cafeRequestDto.phoneNumber!!,
            description = cafeRequestDto.description!!,
            cafeMenuRequestList = cafeRequestDto.cafeMenuList
        )

        CafeTestUtils.assertCafeEquals(request = cafeRequestDto, entity = cafe)
    }

    private fun createCreateCafeRequestDto(): CafeInfoRequestDto {
        val cafeName = "beanie_cafe"
        val cafeAddress = "beanie_cafe_address"
        val phoneNumber = "01012345678"
        val description = "beanie_cafe_description"

        val sizeOptionDetailList = listOf(
            OptionDetailInfoRequestDto(name = "medium", extraPrice = BigDecimal.ZERO),
            OptionDetailInfoRequestDto(name = "large", extraPrice = BigDecimal.valueOf(200L)),
            OptionDetailInfoRequestDto(name = "venti", extraPrice = BigDecimal.valueOf(700L))
        )
        val sizeMenuOption = MenuOptionInfoRequestDto(
            title = "size",
            optionDetailList = sizeOptionDetailList
        )

        val cafeMenuList = listOf(
            CafeMenuInfoRequestDto(
                name = "menu1",
                price = BigDecimal.valueOf(2_800L),
                menuOptionList = listOf(sizeMenuOption)
            ),
            CafeMenuInfoRequestDto(
                name = "menu2",
                price = BigDecimal.valueOf(3_500L),
                menuOptionList = listOf(sizeMenuOption)
            ),
        )

        return CafeInfoRequestDto(
            name = cafeName,
            address = cafeAddress,
            phoneNumber = phoneNumber,
            description = description,
            cafeMenuList = cafeMenuList
        )
    }
}