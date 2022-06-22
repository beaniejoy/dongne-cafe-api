package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeInfoResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeSearchResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CafeService(
    private val cafeRepository: CafeRepository
) {

    @Transactional(readOnly = true)
    fun getCafeList(pageable: Pageable): Page<CafeSearchResponseDto> {
        val cafeListWithPagination = cafeRepository.findAll(pageable)

        return cafeListWithPagination.map { CafeSearchResponseDto.of(it) }
    }

    @Transactional(readOnly = true)
    fun getCafeInfoByCafeId(id: Long): CafeInfoResponseDto {
        val cafe = cafeRepository.findByIdOrNull(id)
            ?: throw CafeNotFoundException(id)

        return CafeInfoResponseDto.of(cafe)
    }

    fun updateCafe(
        id: Long,
        name: String,
        address: String,
        phoneNumber: String,
        description: String,
    ) {
        val cafe = cafeRepository.findByIdOrNull(id)
            ?: throw CafeNotFoundException(id)

        cafe.updateInfo(
            name = name,
            address = address,
            phoneNumber = phoneNumber,
            description = description
        )
    }
}