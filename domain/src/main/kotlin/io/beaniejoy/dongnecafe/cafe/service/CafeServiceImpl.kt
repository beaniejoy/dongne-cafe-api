package io.beaniejoy.dongnecafe.cafe.service

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.cafe.model.CafeInfo
import io.beaniejoy.dongnecafe.cafe.model.CafeInfoMapper
import io.beaniejoy.dongnecafe.cafe.model.CafeQuery
import io.beaniejoy.dongnecafe.cafe.persistence.CafeReaderPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesStorePort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeStorePort
import mu.KLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeServiceImpl(
    private val cafeStorePort: CafeStorePort,
    private val cafeReaderPort: CafeReaderPort,
    private val cafeSeriesStorePort: CafeSeriesStorePort,
    private val cafeInfoMapper: CafeInfoMapper
) : CafeService {

    companion object : KLogging()

    /**
     * 카페 생성 로직
     * - 카페 생성시 카페정보 뿐만 아니라 하위 메뉴정보, 옵션, 옵션상세 같이 생성
     * - 카페 정보(이름, 주소, 전화번호, 소개글)
     * - 카페 카테고리(이름, 설명)
     * - 카페 메뉴정보 (메뉴 이름, 가격 /ex. 아메리카노, 2,800)
     * - 메뉴 옵션 (옵션 이름 /ex. 사이즈)
     * - 옵션 상세 (상세 이름, 추가 금액 /ex. [(medium, 0), (large, 200), (venti, 700)])
     */
    @Transactional
    override fun registerCafe(command: CafeCommand.RegisterCafe): CafeInfo.RegisteredCafe {
        if (cafeReaderPort.existsCafeByName(command.name)) {
            throw BusinessException(ErrorCode.CAFE_EXISTED)
        }

        val savedCafe = cafeStorePort.store(cafe = Cafe.createEntity(command))

        cafeSeriesStorePort.storeMenuCategorySeries(
            cafe = savedCafe,
            registerCafe = command
        )

        return cafeInfoMapper.of(savedCafe)
    }

    @Transactional(readOnly = true)
    override fun searchCafes(param: CafeQuery.SearchCafesParam, pageable: Pageable): Page<CafeInfo.CafeSearchInfo> {
        val cafes: Page<Cafe> = cafeReaderPort.getCafesPageByParams(
            name = param.name,
            pageable = pageable
        )

        return cafes.map {
            cafeInfoMapper.cafeSearchInfoOf(
                cafe = it,
                cafeImages = it.cafeImages
            )
        }
    }

    @Transactional(readOnly = true)
    override fun getDetailedCafe(id: Long): CafeInfo.CafeDetailedInfo {
        val cafe = cafeReaderPort.getCafe(id)
            ?: throw BusinessException(ErrorCode.CAFE_NOT_FOUND)

        return cafeInfoMapper.cafeDetailedInfoOf(
            cafe = cafe,
            cafeMenuCategories = cafe.cafeMenuCategories,
            cafeImages = cafe.cafeImages
        )
    }

    /**
     * 카페 정보 수정
     * - 카페 정보만 수정 (하위 엔티티에 대해서는 각 도메인 영역에서 수정)
     */
    @Transactional
    override fun updateCafe(id: Long, command: CafeCommand.UpdateCafe) {
        val cafe = cafeReaderPort.getCafe(id)
            ?: throw BusinessException(ErrorCode.CAFE_NOT_FOUND)

        cafe.updateEntity(command)
    }
}