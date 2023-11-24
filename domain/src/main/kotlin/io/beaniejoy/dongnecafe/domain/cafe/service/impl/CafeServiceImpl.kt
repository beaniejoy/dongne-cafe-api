package io.beaniejoy.dongnecafe.domain.cafe.service.impl

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeInfo
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeInfoMapper
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeQuery
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeReaderPort
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeStorePort
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeService
import io.beaniejoy.dongnecafe.domain.cafe.service.validator.CafeValidator
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeServiceImpl(
    private val cafeStorePort: CafeStorePort,
    private val cafeReaderPort: CafeReaderPort,
    private val cafeInfoMapper: CafeInfoMapper,
    private val cafeValidator: CafeValidator
) : CafeService {
    @Transactional
    override fun registerCafe(command: CafeCommand.RegisterCafe): CafeInfo.RegisteredCafe {
        cafeValidator.validateNotExisted(command.name)
        val savedCafe = cafeStorePort.store(Cafe.createEntity(command))
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

    // TODO cafe, image, category, menu 적절히 분리해서 join fetch로 성능 개선해보기
    // 대표 이미지 해야할지 고민(카페 메뉴에 대해서) >> 왜냐하면 최초 카페 진입시 모든 카페 메뉴 이미지 내용 가져오려면 방대한 데이터가 필요
    // 카테고리 5개, 카테고리당 메뉴 4개, 메뉴당 이미지 3개, 메뉴당 옵션 2개,  옵션당 상세 2개 = 5 * 4 * 3 * 2 * 2 = 240개 데이터 응답
    // 카테고리는 이미지 한 개로 고정
    @Transactional(readOnly = true)
    override fun getDetailedCafe(name: String): CafeInfo.CafeDetailedInfo {
        val cafe = cafeReaderPort.getCafeNotNullByName(name)

        return cafeInfoMapper.cafeDetailedInfoOf(cafe)
    }

    /**
     * 카페 정보 수정
     * - 카페 정보만 수정 (하위 엔티티에 대해서는 각 도메인 영역에서 수정)
     */
    @Transactional
    override fun updateCafe(id: Long, command: CafeCommand.UpdateCafe) {
        val cafe = cafeReaderPort.getCafeNotNull(id)
        cafe.update(command)
    }
}
