package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeMenuDetailedInfo
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeMenuNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeMenuService(
    private val cafeMenuRepository: CafeMenuRepository
) {
    @Transactional(readOnly = true)
    fun getCafeMenuInfoByCafeIdAndMenuId(menuId: Long, cafeId: Long): CafeMenuDetailedInfo {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId, cafeId)

        return CafeMenuDetailedInfo.of(cafeMenu)
    }
}