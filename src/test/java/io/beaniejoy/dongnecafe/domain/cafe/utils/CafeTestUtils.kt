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

class CafeTestUtils {
    companion object {
        fun assertCafeEquals(request: CafeRegisterRequest, entity: Cafe) {
            assertEquals(request.name, entity.name)
            assertEquals(request.address, entity.address)
            assertEquals(request.phoneNumber, entity.phoneNumber)
            assertEquals(request.description, entity.description)

            assertCafeMenuListEquals(request.cafeMenuList, entity.cafeMenuList)
        }

        private fun assertCafeMenuListEquals(
            cafeMenuRequestList: List<CafeMenuRegisterRequest>,
            cafeMenuList: List<CafeMenu>,
        ) {
            for (index in cafeMenuRequestList.indices) {
                assertEquals(cafeMenuRequestList[index].name, cafeMenuList[index].name)
                assertEquals(cafeMenuRequestList[index].price, cafeMenuList[index].price)

                assertMenuOptionListEquals(
                    cafeMenuRequestList[index].menuOptionList,
                    cafeMenuList[index].menuOptionList
                )
            }
        }

        private fun assertMenuOptionListEquals(
            menuOptionRequestList: List<MenuOptionRegisterRequest>,
            menuOptionList: List<MenuOption>,
        ) {
            for (index in menuOptionRequestList.indices) {
                assertEquals(menuOptionRequestList[index].title, menuOptionList[index].title)

                assertOptionDetailListEquals(
                    menuOptionRequestList[index].optionDetailList,
                    menuOptionList[index].optionDetailList
                )
            }
        }

        private fun assertOptionDetailListEquals(
            optionDetailRequestList: List<OptionDetailRegisterRequest>,
            optionDetailList: MutableList<OptionDetail>,
        ) {
            for (index in optionDetailRequestList.indices) {
                assertEquals(optionDetailRequestList[index].name, optionDetailList[index].name)
                assertEquals(optionDetailRequestList[index].extraPrice, optionDetailList[index].extraPrice)
            }
        }

        fun createCafeRegisterRequest(): CafeRegisterRequest {
            val cafeName = "beanie_cafe"
            val cafeAddress = "beanie_cafe_address"
            val phoneNumber = "01012345678"
            val description = "beanie_cafe_description"

            val sizeOptionDetailList = listOf(
                OptionDetailRegisterRequest(name = "medium", extraPrice = BigDecimal.ZERO),
                OptionDetailRegisterRequest(name = "large", extraPrice = BigDecimal.valueOf(200L)),
                OptionDetailRegisterRequest(name = "venti", extraPrice = BigDecimal.valueOf(700L))
            )
            val sizeMenuOption = MenuOptionRegisterRequest(
                title = "size",
                optionDetailList = sizeOptionDetailList
            )

            val cafeMenuList = listOf(
                CafeMenuRegisterRequest(
                    name = "menu1",
                    price = BigDecimal.valueOf(2_800L),
                    menuOptionList = listOf(sizeMenuOption)
                ),
                CafeMenuRegisterRequest(
                    name = "menu2",
                    price = BigDecimal.valueOf(3_500L),
                    menuOptionList = listOf(sizeMenuOption)
                ),
            )

            return CafeRegisterRequest(
                name = cafeName,
                address = cafeAddress,
                phoneNumber = phoneNumber,
                description = description,
                cafeMenuList = cafeMenuList
            )
        }
    }
}