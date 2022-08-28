package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeMenuDetailedInfo
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeMenuNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeMenuService(
    private val cafeMenuRepository: CafeMenuRepository,
    private val menuOptionService: MenuOptionService
) {
    fun getDetailedInfoByMenuId(menuId: Long, cafeId: Long): CafeMenuDetailedInfo {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId = menuId, cafeId = cafeId)

        return CafeMenuDetailedInfo.of(cafeMenu)
    }

    @Transactional
    fun updateInfoAndBulkUpdate(menuId: Long, cafeId: Long, resource: CafeMenuUpdateRequest) {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId = menuId, cafeId = cafeId)

        cafeMenu.updateInfo(name = resource.name!!, price = resource.price)

        menuOptionService.bulkUpdate(resource.menuOptionList)
    }

    @Transactional
    fun deleteByCafeMenuId(menuId: Long, cafeId: Long) {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId = menuId, cafeId = cafeId)

        cafeMenuRepository.delete(cafeMenu)
    }

    @Transactional
    fun bulkDelete(cafeId: Long, cafeMenuIdList: List<Long>) {
        cafeMenuIdList.forEach {
            deleteByCafeMenuId(menuId = it, cafeId = cafeId)
        }
    }
}