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
                OptionDetailRegisterRequest(name = "large", extraPrice = BigDecimal.valueOf(200L)),
                OptionDetailRegisterRequest(name = "venti", extraPrice = BigDecimal.valueOf(700L))
            )
            val sizeMenuOption = MenuOptionRegisterRequest(
                title = "size",
                optionDetailList = sizeOptionDetailList
            )

            return listOf(
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