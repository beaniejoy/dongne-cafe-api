package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeInfoResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeSearchResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeMenuInfoRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption
import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeExistedException
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeRepository
import mu.KLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeService(
    private val cafeRepository: CafeRepository,
) {
    companion object : KLogging()

    /**
     * 카페 생성 로직
     */
    fun createCafe(
        name: String,
        address: String,
        phoneNumber: String,
        description: String,
        cafeMenuRequestList: List<CafeMenuInfoRequestDto>,
    ): Long {
        checkCafeExistedByName(name)

        val cafe = Cafe.createCafe(
            name = name,
            address = address,
            phoneNumber = phoneNumber,
            description = description,
            cafeMenuRequestList = cafeMenuRequestList
        )

        val savedCafe = cafeRepository.save(cafe)

        return savedCafe.id
    }

    private fun checkCafeExistedByName(name: String) {
        val findCafe = cafeRepository.findByName(name)
        if (findCafe != null) {
            throw CafeExistedException(name)
        }
    }

    fun getCafeList(pageable: Pageable): Page<CafeSearchResponseDto> {
        val cafeList: Page<Cafe> = cafeRepository.findAll(pageable)

        return cafeList.map { CafeSearchResponseDto.of(it) }
    }

    fun getCafeInfoByCafeId(id: Long): CafeInfoResponseDto {
        val cafe = cafeRepository.findByIdOrNull(id)
            ?: throw CafeNotFoundException(id)

        return CafeInfoResponseDto.of(cafe)
    }

    @Transactional
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