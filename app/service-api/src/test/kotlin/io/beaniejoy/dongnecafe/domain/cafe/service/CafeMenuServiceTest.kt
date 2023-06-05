package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuRepository
import io.beaniejoy.dongnecafe.domain.cafe.repository.MenuOptionRepository
import io.beaniejoy.dongnecafe.domain.cafe.repository.OptionDetailRepository
import io.beaniejoy.dongnecafe.utils.CafeMenuTestUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class CafeMenuServiceTest {
    @Mock
    lateinit var mockCafeMenuRepository: CafeMenuRepository

    @Mock
    lateinit var mockMenuOptionRepository: MenuOptionRepository

    @Mock
    lateinit var mockOptionDetailRepository: OptionDetailRepository

    @Mock
    lateinit var mockOptionDetailService: OptionDetailService

    @Mock
    lateinit var mockMenuOptionService: MenuOptionService

    @InjectMocks
    lateinit var mockCafeMenuService: CafeMenuService

    @Disabled
    @Test
    @DisplayName("카페 메뉴 ID를 통한 카페 메뉴 상세 조회")
    fun find_cafe_menu_by_cafe_menu_id() {
        // given
        val (name, price, menuOptions) = CafeMenuTestUtils.createCafeMenuRegisterRequest()
        val cafeMenu = CafeMenu.createEntity(
            name = name!!,
            price = price
        )

        val findCafeId = 100L
        val findCafeMenuId = 1001L

        `when`(mockCafeMenuRepository.findById(findCafeMenuId))
            .thenReturn(Optional.of(cafeMenu.apply {
                // cafe_menu_id Reflection 주입
                CafeMenuTestUtils.injectCafeMenuId(this, findCafeMenuId)
            }))

        // when
        val cafeMenuDetailedInfo = mockCafeMenuService.getDetailedInfoByMenuId(findCafeMenuId, findCafeId)

        // then
        verify(mockCafeMenuRepository).findById(findCafeMenuId)

        cafeMenuDetailedInfo.also {
            assertEquals(findCafeMenuId, it.cafeMenuId)
            assertEquals(name, it.name)
            assertEquals(price, it.price)
            // TODO: MenuOption list도 비교 검증해야되는지
        }
    }

    @Test
    @DisplayName("존재하지 않는 카페 조회에 대한 예외 발생 테스트")
    fun find_cafe_menu_by_cafe_menu_id_when_not_existed() {
        // given
        val findCafeId = 100L
        val findCafeMenuId = 1001L
        `when`(mockCafeMenuRepository.findById(findCafeMenuId)).thenReturn(Optional.empty())

        // then
        val exception = assertThrows<BusinessException> {
            // when
            mockCafeMenuService.getDetailedInfoByMenuId(findCafeMenuId, findCafeId)
        }

        assertEquals(ErrorCode.CAFE_MENU_NOT_FOUND, exception.errorCode)

        verify(mockCafeMenuRepository).findById(findCafeMenuId)
    }
}
