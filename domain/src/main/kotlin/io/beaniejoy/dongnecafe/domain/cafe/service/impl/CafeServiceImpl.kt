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
import org.springframework.cache.annotation.Cacheable
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

    /**
     * Cafe, CafeMenuCategory > fetch join
     * others > in query with batch_size
     */
    // TODO test for sync and distributed instances sync
    @Cacheable(cacheNames = ["cafeDetail"], key = "#name")
    @Transactional(readOnly = true)
    override fun getDetailedCafe(name: String): CafeInfo.CafeDetailInfo {
        val cafe = cafeReaderPort.getCafeNotNullWithCategoryFetch(name)

        return cafeInfoMapper.cafeDetailInfoOf(cafe)
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
