package io.beaniejoy.dongnecafe.cafe.service

//import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
//import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
//import io.beaniejoy.dongnecafe.cafe.model.CafeRequestDto
//import io.beaniejoy.dongnecafe.cafe.model.request.CafeMenuUpdateRequest
//import io.beaniejoy.dongnecafe.cafe.model.response.CafeMenuDetailedInfo
//import io.beaniejoy.dongnecafe.cafe.repository.CafeMenuRepository
//import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
//import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
//import org.springframework.data.repository.findByIdOrNull
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//import java.math.BigDecimal

// TODO
//  - cafeMenu에 대한 생성, 수정, 삭제를 단일 카페 상세 페이지 내에서 한꺼번에 수행할지 고민
//  - front 개발 후에 CafeMenu에 대한 전체적인 로직 수정이 필요해 보임
//@Service
//@Transactional
//class CafeMenuService(
//    private val cafeMenuRepository: CafeMenuRepository,
//    private val menuOptionService: MenuOptionService
//) {
//    @Transactional(readOnly = true)
//    fun getDetailedInfoByMenuId(menuId: Long, cafeId: Long): CafeMenuDetailedInfo {
//        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
//            ?: throw BusinessException(ErrorCode.CAFE_MENU_NOT_FOUND)
//
//        return CafeMenuDetailedInfo.of(cafeMenu)
//    }
//
//    fun createNewOfCategory(
//        name: String,
//        price: BigDecimal,
//        menuOptionRegisterRequests: List<CafeRequestDto.RegisterMenuOptionRequest>,
//        menuCategory: CafeMenuCategory
//    ): CafeMenu {
//        val cafeMenu = CafeMenu.createEntity(
//            name = name,
//            price = price
//        ).apply {
//            this.updateCafeMenuCategory(menuCategory)
//        }
//
//        val savedCafeMenu = cafeMenuRepository.save(cafeMenu)
//
//        menuOptionRegisterRequests.forEach {
//            menuOptionService.createNewOfMenu(
//                title = it.title,
//                optionDetailRegisterRequests = it.optionDetails,
//                cafeMenu = savedCafeMenu
//            )
//        }
//
//        return savedCafeMenu
//    }
//
//    fun updateInfoAndBulkUpdate(menuId: Long, cafeId: Long, resource: CafeMenuUpdateRequest) {
//        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
//            ?: throw BusinessException(ErrorCode.CAFE_MENU_NOT_FOUND)
//
//        cafeMenu.updateInfo(name = resource.name!!, price = resource.price)
//
//        menuOptionService.bulkUpdate(resource.menuOptions)
//    }
//
//    fun deleteByCafeMenuId(menuId: Long, cafeId: Long) {
//        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
//            ?: throw BusinessException(ErrorCode.CAFE_MENU_NOT_FOUND)
//
//        cafeMenuRepository.delete(cafeMenu)
//    }
//
//    fun bulkDelete(cafeId: Long, cafeMenuIds: List<Long>) {
//        cafeMenuIds.forEach {
//            deleteByCafeMenuId(menuId = it, cafeId = cafeId)
//        }
//    }
//}