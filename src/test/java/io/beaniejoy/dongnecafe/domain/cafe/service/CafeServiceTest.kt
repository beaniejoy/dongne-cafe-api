package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeMenuInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.MenuOptionInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.OptionDetailInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import javax.persistence.GeneratedValue

@ExtendWith(MockitoExtension::class)
internal class CafeServiceTest {
    @InjectMocks
    lateinit var mockCafeService: CafeService

    @Mock
    lateinit var mockCafeRepository: CafeRepository

    @Test
    @DisplayName("카페 신규 생성 테스트")
    fun createCafeTest() {
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

        val savedMockCafeId = 100L

        `when`(mockCafeRepository.findByName(cafeName)).thenReturn(null)
        `when`(mockCafeRepository.save(any(Cafe::class.java))).thenAnswer {
            val argument = it.getArgument<Cafe>(0)
            val idField = argument.javaClass.declaredFields
                .find { f ->
                    f.getAnnotation(GeneratedValue::class.java) != null
                } ?: return@thenAnswer argument

            idField.isAccessible = true
            idField.set(argument, savedMockCafeId)

            return@thenAnswer argument
        }

        val savedCafeId = mockCafeService.createCafe(
            name = cafeName,
            address = cafeAddress,
            phoneNumber = phoneNumber,
            description = description,
            cafeMenuRequestList = cafeMenuList
        )

        verify(mockCafeRepository).findByName(cafeName)
        verify(mockCafeRepository).save(any(Cafe::class.java))

        assertEquals(savedCafeId, savedMockCafeId)
    }
}