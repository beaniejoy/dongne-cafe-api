package io.beaniejoy.dongnecafe.domain.cafe.utils

import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeMenuInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.MenuOptionInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.OptionDetailInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption
import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail
import org.junit.jupiter.api.Assertions

class CafeTestUtils {
    companion object {
        fun assertCafeEquals(request: CafeInfoRequestDto, entity: Cafe) {
            Assertions.assertEquals(request.name, entity.name)
            Assertions.assertEquals(request.address, entity.address)
            Assertions.assertEquals(request.phoneNumber, entity.phoneNumber)
            Assertions.assertEquals(request.description, entity.description)

            assertCafeMenuListEquals(request.cafeMenuList, entity.cafeMenuList)
        }

        private fun assertCafeMenuListEquals(
            cafeMenuRequestList: List<CafeMenuInfoRequestDto>,
            cafeMenuList: List<CafeMenu>,
        ) {
            for (index in cafeMenuRequestList.indices) {
                Assertions.assertEquals(cafeMenuRequestList[index].name, cafeMenuList[index].name)
                Assertions.assertEquals(cafeMenuRequestList[index].price, cafeMenuList[index].price)

                assertMenuOptionListEquals(cafeMenuRequestList[index].menuOptionList, cafeMenuList[index].menuOptionList)
            }
        }

        private fun assertMenuOptionListEquals(
            menuOptionRequestList: List<MenuOptionInfoRequestDto>,
            menuOptionList: List<MenuOption>,
        ) {
            for (index in menuOptionRequestList.indices) {
                Assertions.assertEquals(menuOptionRequestList[index].title, menuOptionList[index].title)

                assertOptionDetailListEquals(
                    menuOptionRequestList[index].optionDetailList,
                    menuOptionList[index].optionDetailList
                )
            }
        }

        private fun assertOptionDetailListEquals(
            optionDetailRequestList: List<OptionDetailInfoRequestDto>,
            optionDetailList: MutableList<OptionDetail>,
        ) {
            for (index in optionDetailRequestList.indices) {
                Assertions.assertEquals(optionDetailRequestList[index].name, optionDetailList[index].name)
                Assertions.assertEquals(optionDetailRequestList[index].extraPrice, optionDetailList[index].extraPrice)

            }
        }
    }
}