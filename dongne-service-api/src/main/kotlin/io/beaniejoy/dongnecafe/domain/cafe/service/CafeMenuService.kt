package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeMenuDetailedInfo
import io.beaniejoy.dongnecafe.common.error.exception.CafeMenuNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

// TODO
//  - cafeMenu에 대한 생성, 수정, 삭제를 단일 카페 상세 페이지 내에서 한꺼번에 수행할지 고민
//  - front 개발 후에 CafeMenu에 대한 전체적인 로직 수정이 필요해 보임
@Service
@Transactional
class CafeMenuService(
    private val cafeMenuRepository: CafeMenuRepository,
    private val menuOptionService: MenuOptionService
) {
    @Transactional(readOnly = true)
    fun getDetailedInfoByMenuId(menuId: Long, cafeId: Long): CafeMenuDetailedInfo {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId = menuId, cafeId = cafeId)

        return CafeMenuDetailedInfo.of(cafeMenu)
    }

    fun updateInfoAndBulkUpdate(menuId: Long, cafeId: Long, resource: CafeMenuUpdateRequest) {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId = menuId, cafeId = cafeId)

        cafeMenu.updateInfo(name = resource.name!!, price = resource.price)

        menuOptionService.bulkUpdate(resource.menuOptionList)
    }

    fun deleteByCafeMenuId(menuId: Long, cafeId: Long) {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId = menuId, cafeId = cafeId)

        cafeMenuRepository.delete(cafeMenu)
    }

    fun bulkDelete(cafeId: Long, cafeMenuIdList: List<Long>) {
        cafeMenuIdList.forEach {
            deleteByCafeMenuId(menuId = it, cafeId = cafeId)
        }
    }
}