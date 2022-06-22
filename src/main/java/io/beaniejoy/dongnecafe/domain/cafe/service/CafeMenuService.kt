package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.dto.menu.CafeMenuDetailResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeMenuNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class CafeMenuService(
    private val cafeMenuRepository: CafeMenuRepository
) {
    @Transactional(readOnly = true)
    fun getCafeMenuInfoByCafeIdAndMenuId(menuId: Long, cafeId: Long): CafeMenuDetailResponseDto {
        val cafeMenu = cafeMenuRepository.findByIdOrNull(menuId)
            ?: throw CafeMenuNotFoundException(menuId, cafeId)

        return CafeMenuDetailResponseDto.of(cafeMenu)
    }
}