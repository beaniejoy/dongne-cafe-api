//package io.beaniejoy.dongnecafe.utils
//
//import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
//import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuCategoryRegisterRequest
////import org.junit.jupiter.api.Assertions
//
//class CafeMenuCategoryTestUtils {
//    companion object {
//        fun createCafeMenuCategoryRegisterRequests(): List<CafeMenuCategoryRegisterRequest> {
//            return listOf(
//                CafeMenuCategoryRegisterRequest(
//                    name = "카테고리_테스트_1",
//                    description = "카테고리 설명 내용",
//                    cafeMenus = CafeMenuTestUtils.createCafeMenuRegisterRequests()
//                )
//            )
//        }
//
//        fun assertCafeMenuCategoriesEquals(
//            cafeMenuCategoriesRequests: List<CafeMenuCategoryRegisterRequest>,
//            cafeMenuCategories: List<CafeMenuCategory>,
//        ) {
//            for (index in cafeMenuCategoriesRequests.indices) {
////                Assertions.assertEquals(cafeMenuCategoriesRequests[index].name, cafeMenuCategories[index].name)
////                Assertions.assertEquals(cafeMenuCategoriesRequests[index].description, cafeMenuCategories[index].description)
//
//                CafeMenuTestUtils.assertCafeMenusEquals(
//                    cafeMenuRequests = cafeMenuCategoriesRequests[index].cafeMenus,
//                    cafeMenus = cafeMenuCategories[index].cafeMenus
//                )
//            }
//        }
//    }
//}