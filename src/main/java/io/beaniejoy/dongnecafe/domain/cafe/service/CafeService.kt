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
     * - 카페 생성시 카페정보 뿐만 아니라 하위 메뉴정보, 옵션, 옵션상세 같이 생성
     * - 카페 정보(이름, 주소, 전화번호, 소개글)
     * - 카페 메뉴정보 (메뉴 이름, 가격 /ex. 아메리카노, 2,800)
     * - 메뉴 옵션 (옵션 이름 /ex. 사이즈)
     * - 옵션 상세 (상세 이름, 추가 금액 /ex. [(medium, 0), (large, 200), (venti, 700)])
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

    /**
     * 카페 정보 수정
     * - 카페 정보만 수정 (하위 엔티티에 대해서는 각 도메인 영역에서 수정)
     */
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