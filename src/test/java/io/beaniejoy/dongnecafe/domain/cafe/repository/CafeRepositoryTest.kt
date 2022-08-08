package io.beaniejoy.dongnecafe.domain.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeMenuInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.MenuOptionInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.OptionDetailInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.utils.CafeTestUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal

@DataJpaTest
internal class CafeRepositoryTest {
    @Autowired
    lateinit var cafeRepository: CafeRepository

    @Test
    @DisplayName("[JPA] 신규 Cafe 저장 테스트")
    fun saveTest() {
        val cafeRequestDto = createCreateCafeRequestDto()
        val cafe = cafeRequestDto.let {
            Cafe.createCafe(
                name = it.name!!,
                address = it.address!!,
                phoneNumber = it.phoneNumber!!,
                description = it.description!!,
                cafeMenuRequestList = it.cafeMenuList
            )
        }

        val savedCafe = cafeRepository.save(cafe)

        assertEquals(1L, savedCafe.id)
        CafeTestUtils.assertCafeEquals(cafeRequestDto, savedCafe)
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