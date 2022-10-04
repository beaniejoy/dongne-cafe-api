package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.OptionDetailUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.utils.CafeTestUtils
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
class CafeMenuServiceIntegratedTest {
    @Autowired
    lateinit var cafeService: CafeService

    @Autowired
    lateinit var cafeMenuService: CafeMenuService

    @Test
    fun update_info_and_bulk_update_menu_options() {
        val cafeRegisterRequest = CafeTestUtils.createCafeRegisterRequest()

        val savedCafeId = cafeService.createNew(
            name = cafeRegisterRequest.name!!,
            address = cafeRegisterRequest.address!!,
            phoneNumber = cafeRegisterRequest.phoneNumber!!,
            description = cafeRegisterRequest.description!!,
            cafeMenuRequestList = cafeRegisterRequest.cafeMenuList
        )

        val cafeDetailedInfo = cafeService.getDetailedInfoByCafeId(savedCafeId)

        val cafeMenuUpdateRequest = cafeMenuService.getDetailedInfoByMenuId(
            menuId = cafeDetailedInfo.menuList[0].cafeMenuId,
            cafeId = cafeDetailedInfo.cafeId
        ).let {
            CafeMenuUpdateRequest(
                name = "menu2 update",
                price = it.price,
                menuOptionList = listOf(it.optionList[1].let { menuOption ->
                    MenuOptionUpdateRequest(
                        menuOptionId = menuOption.menuOptionId,
                        title = "샷 update",
                        optionDetailList = menuOption.optionDetailList.mapIndexed { index, optionDetail ->
                            OptionDetailUpdateRequest(
                                optionDetailId = optionDetail.optionDetailId,
                                name = optionDetail.name!!,
                                extraPrice = BigDecimal("${index}000"),
                                isDelete = index == 0
                            )
                        }
                    )
                })
            )
        }

        cafeMenuService.updateInfoAndBulkUpdate(
            menuId = cafeDetailedInfo.menuList[1].cafeMenuId,
            cafeId = cafeDetailedInfo.cafeId,
            resource = cafeMenuUpdateRequest
        )
    }
}