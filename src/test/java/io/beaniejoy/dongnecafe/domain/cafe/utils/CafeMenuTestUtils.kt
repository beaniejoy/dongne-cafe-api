package io.beaniejoy.dongnecafe.domain.cafe.utils

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption
import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.OptionDetailRegisterRequest
import org.junit.jupiter.api.Assertions
import java.math.BigDecimal
import javax.persistence.GeneratedValue

class CafeMenuTestUtils {
    companion object {
        fun createCafeMenuRegisterRequest(): CafeMenuRegisterRequest {
            return createCafeMenuRegisterRequestList()[0]
        }

        fun createCafeMenuRegisterRequestList(): List<CafeMenuRegisterRequest> {
            val sizeOptionDetailList = listOf(
                OptionDetailRegisterRequest(name = "medium", extraPrice = BigDecimal.ZERO),
                OptionDetailRegisterRequest(name = "large", extraPrice = BigDecimal("200")),
                OptionDetailRegisterRequest(name = "venti", extraPrice = BigDecimal("700"))
            )
            val shotOptionDetailList = listOf(
                OptionDetailRegisterRequest(name = "기본", extraPrice = BigDecimal.ZERO),
                OptionDetailRegisterRequest(name = "샷 1 추가", extraPrice = BigDecimal("500")),
                OptionDetailRegisterRequest(name = "샷 2 추가", extraPrice = BigDecimal("1000")),
            )

            val sizeMenuOption = MenuOptionRegisterRequest(
                title = "사이즈",
                optionDetailList = sizeOptionDetailList
            )
            val shotMenuOption = MenuOptionRegisterRequest(
                title = "샷",
                optionDetailList = shotOptionDetailList
            )

            return listOf(
                CafeMenuRegisterRequest(
                    name = "menu1",
                    price = BigDecimal("2800"),
                    menuOptionList = listOf(sizeMenuOption, shotMenuOption)
                ),
                CafeMenuRegisterRequest(
                    name = "menu2",
                    price = BigDecimal("3500"),
                    menuOptionList = listOf(sizeMenuOption)
                ),
            )
        }

        fun assertCafeMenuListEquals(
            cafeMenuRequestList: List<CafeMenuRegisterRequest>,
            cafeMenuList: List<CafeMenu>,
        ) {
            for (index in cafeMenuRequestList.indices) {
                Assertions.assertEquals(cafeMenuRequestList[index].name, cafeMenuList[index].name)
                Assertions.assertEquals(cafeMenuRequestList[index].price, cafeMenuList[index].price)

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
                Assertions.assertEquals(menuOptionRequestList[index].title, menuOptionList[index].title)

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
                Assertions.assertEquals(optionDetailRequestList[index].name, optionDetailList[index].name)
                Assertions.assertEquals(optionDetailRequestList[index].extraPrice, optionDetailList[index].extraPrice)
            }
        }

        fun injectCafeMenuId(
            cafeMenu: CafeMenu,
            newCafeMenuId: Long,
        ): CafeMenu {
            val idField = cafeMenu.javaClass.declaredFields
                .find { f ->
                    f.getAnnotation(GeneratedValue::class.java) != null
                } ?: return cafeMenu

            idField.isAccessible = true
            idField.set(cafeMenu, newCafeMenuId)

            return cafeMenu
        }
    }
}