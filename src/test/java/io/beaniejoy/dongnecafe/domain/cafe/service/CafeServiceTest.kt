package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeMenuInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.MenuOptionInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.OptionDetailInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeExistedException
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.util.*
import javax.persistence.GeneratedValue

@ExtendWith(MockitoExtension::class)
@TestMethodOrder(MethodOrderer.DisplayName::class)
internal class CafeServiceTest {
    @InjectMocks
    lateinit var mockCafeService: CafeService

    @Mock
    lateinit var mockCafeRepository: CafeRepository

    @Test
    @DisplayName("카페 신규 생성 테스트")
    fun create_cafe_test() {
        // given
        val cafeRequestDto = createCreateCafeRequestDto()
        val savedMockCafeId = 100L

        `when`(mockCafeRepository.findByName(cafeRequestDto.name!!)).thenReturn(null)
        `when`(mockCafeRepository.save(any(Cafe::class.java))).thenAnswer {
            injectCafeId(it.getArgument(0), savedMockCafeId)
        }

        // when
        val savedCafeId = mockCafeService.createCafe(
            name = cafeRequestDto.name!!,
            address = cafeRequestDto.address!!,
            phoneNumber = cafeRequestDto.phoneNumber!!,
            description = cafeRequestDto.description!!,
            cafeMenuRequestList = cafeRequestDto.cafeMenuList
        )

        // then
        verify(mockCafeRepository).findByName(cafeRequestDto.name!!) // TODO eq 에러 발생 이유
        verify(mockCafeRepository).save(any(Cafe::class.java))

        assertEquals(savedCafeId, savedMockCafeId)
    }

    @Test
    @DisplayName("카페 신규 생성시 이미 존재하는 카페 예외 발생 테스트")
    fun fail_create_cafe_when_existed() {
        // given
        val cafeRequestDto = createCreateCafeRequestDto()
        val cafe = Cafe.createCafe(
            name = cafeRequestDto.name!!,
            address = cafeRequestDto.address!!,
            phoneNumber = cafeRequestDto.phoneNumber!!,
            description = cafeRequestDto.description!!,
            cafeMenuRequestList = cafeRequestDto.cafeMenuList
        )

        `when`(mockCafeRepository.findByName(cafeRequestDto.name!!)).thenReturn(cafe)

        // then
        assertThrows<CafeExistedException> {
            // when
            mockCafeService.createCafe(
                name = cafeRequestDto.name!!,
                address = cafeRequestDto.address!!,
                phoneNumber = cafeRequestDto.phoneNumber!!,
                description = cafeRequestDto.description!!,
                cafeMenuRequestList = cafeRequestDto.cafeMenuList
            )
        }
    }

    @Test
    @DisplayName("카페 정보 변경 테스트")
    fun update_cafe_test() {
        // given
        val cafeRequestDto = createCreateCafeRequestDto()
        val cafe = Cafe.createCafe(
            name = cafeRequestDto.name!!,
            address = cafeRequestDto.address!!,
            phoneNumber = cafeRequestDto.phoneNumber!!,
            description = cafeRequestDto.description!!,
            cafeMenuRequestList = cafeRequestDto.cafeMenuList
        )
        val cafeId = 50L

        // TODO findByIdOrNull은 kotlin test 라이브러리 필요한 듯
        `when`(mockCafeRepository.findById(cafeId)).thenReturn(Optional.of(cafe))

        // then
        mockCafeService.updateCafe(
            id = cafeId,
            name = "",
            address = "",
            phoneNumber = "",
            description = "",
        )

        verify(mockCafeRepository).findById(eq(cafeId))

        // TODO update TEST 방법?
    }

    @Test
    @DisplayName("카페 정보 변경시 존재하지 않는 카페 예외 발생 테스트")
    fun fail_update_cafe_when_not_found() {
        // given
        val cafeId = 50L

        `when`(mockCafeRepository.findById(cafeId)).thenReturn(Optional.empty())

        assertThrows<CafeNotFoundException> {
            mockCafeService.updateCafe(
                id = cafeId,
                name = "",
                address = "",
                phoneNumber = "",
                description = "",
            )
        }
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

    private fun injectCafeId(
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