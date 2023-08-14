//package io.beaniejoy.dongnecafe.utils
//
//import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
//import io.beaniejoy.dongnecafe.cafe.entity.MenuOption
//import io.beaniejoy.dongnecafe.cafe.entity.OptionDetail
//import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuRegisterRequest
//import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionRegisterRequest
//import io.beaniejoy.dongnecafe.domain.cafe.model.request.OptionDetailRegisterRequest
////import org.junit.jupiter.api.Assertions.assertEquals
//import java.math.BigDecimal
////import javax.persistence.GeneratedValue
//
//class CafeMenuTestUtils {
//    companion object {
//        fun createCafeMenuRegisterRequest(): CafeMenuRegisterRequest {
//            return createCafeMenuRegisterRequests()[0]
//        }
//
//        fun createCafeMenuRegisterRequests(): List<CafeMenuRegisterRequest> {
//            val sizeOptionDetails = listOf(
//                OptionDetailRegisterRequest(name = "medium", extraPrice = BigDecimal.ZERO),
//                OptionDetailRegisterRequest(name = "large", extraPrice = BigDecimal("200")),
//                OptionDetailRegisterRequest(name = "venti", extraPrice = BigDecimal("700"))
//            )
//            val shotOptionDetails = listOf(
//                OptionDetailRegisterRequest(name = "기본", extraPrice = BigDecimal.ZERO),
//                OptionDetailRegisterRequest(name = "샷 1 추가", extraPrice = BigDecimal("500")),
//                OptionDetailRegisterRequest(name = "샷 2 추가", extraPrice = BigDecimal("1000")),
//            )
//
//            val sizeMenuOption = MenuOptionRegisterRequest(
//                title = "사이즈",
//                optionDetails = sizeOptionDetails
//            )
//            val shotMenuOption = MenuOptionRegisterRequest(
//                title = "샷",
//                optionDetails = shotOptionDetails
//            )
//
//            return listOf(
//                CafeMenuRegisterRequest(
//                    name = "menu1",
//                    price = BigDecimal("2800"),
//                    menuOptions = listOf(sizeMenuOption, shotMenuOption)
//                ),
//                CafeMenuRegisterRequest(
//                    name = "menu2",
//                    price = BigDecimal("3500"),
//                    menuOptions = listOf(sizeMenuOption)
//                ),
//            )
//        }
//
//        fun assertCafeMenusEquals(
//            cafeMenuRequests: List<CafeMenuRegisterRequest>,
//            cafeMenus: List<CafeMenu>,
//        ) {
////            assertEquals(cafeMenuRequests.size, cafeMenus.size)
//
//            for (index in cafeMenuRequests.indices) {
////                assertEquals(cafeMenuRequests[index].name, cafeMenus[index].name)
//
//                assertMenuOptionsEquals(
//                    cafeMenuRequests[index].menuOptions,
//                    cafeMenus[index].menuOptions
//                )
//            }
//        }
//
//        private fun assertMenuOptionsEquals(
//            menuOptionRequests: List<MenuOptionRegisterRequest>,
//            menuOptions: MutableList<MenuOption>,
//        ) {
//            for (index in menuOptionRequests.indices) {
////                assertEquals(menuOptionRequests[index].title, menuOptions[index].title)
//
//                assertOptionDetailsEquals(
//                    menuOptionRequests[index].optionDetails,
//                    menuOptions[index].optionDetails
//                )
//            }
//        }
//
//        private fun assertOptionDetailsEquals(
//            optionDetailRequests: List<OptionDetailRegisterRequest>,
//            optionDetails: MutableList<OptionDetail>,
//        ) {
//            for (index in optionDetailRequests.indices) {
////                assertEquals(optionDetailRequests[index].name, optionDetails[index].name)
////                assertEquals(optionDetailRequests[index].extraPrice, optionDetails[index].extraPrice)
//            }
//        }
//
//        fun injectCafeMenuId(
//            cafeMenu: CafeMenu,
//            newCafeMenuId: Long,
//        ): CafeMenu {
////            val idField = cafeMenu.javaClass.declaredFields
////                .find { f ->
////                    f.getAnnotation(GeneratedValue::class.java) != null
////                } ?: return cafeMenu
////
////            idField.isAccessible = true
////            idField.set(cafeMenu, newCafeMenuId)
//
//            return cafeMenu
//        }
//    }
//}
