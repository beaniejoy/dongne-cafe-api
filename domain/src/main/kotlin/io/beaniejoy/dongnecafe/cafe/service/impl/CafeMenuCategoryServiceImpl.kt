package io.beaniejoy.dongnecafe.cafe.service.impl

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.cafe.model.CafeInfo
import io.beaniejoy.dongnecafe.cafe.model.CafeInfoMapper
import io.beaniejoy.dongnecafe.cafe.persistence.CafeReaderPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeRemoverPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesReaderPort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeStorePort
import io.beaniejoy.dongnecafe.cafe.service.CafeMenuCategoryService
import io.beaniejoy.dongnecafe.cafe.service.validator.CafeMenuCategoryValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeMenuCategoryServiceImpl(
    private val cafeReaderPort: CafeReaderPort,
    private val cafeSeriesReaderPort: CafeSeriesReaderPort,
    private val cafeStorePort: CafeStorePort,
    private val cafeRemoverPort: CafeRemoverPort,
    private val cafeInfoMapper: CafeInfoMapper,
    private val cafeMenuCategoryValidator: CafeMenuCategoryValidator
) : CafeMenuCategoryService {
    @Transactional
    override fun registerMenuCategory(
        cafeId: Long,
        command: CafeCommand.RegisterCafeMenuCategory
    ): CafeInfo.RegisteredCafeMenuCategory {
        val cafe = cafeReaderPort.getCafeNotNull(cafeId)

        cafeMenuCategoryValidator.validateNotExisted(
            name = command.name,
            cafeId = cafeId
        )

        val savedCafeMenuCategory = cafeStorePort.store(
            CafeMenuCategory.createEntity(command).apply {
                this.updateCafe(cafe)
            }
        )

        return cafeInfoMapper.of(savedCafeMenuCategory)
    }

    @Transactional
    override fun updateMenuCategory(
        cafeId: Long,
        menuCategoryId: Long,
        command: CafeCommand.UpdateCafeMenuCategory
    ) {
        cafeMenuCategoryValidator.validateTheSameCafe(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId
        )

        val cafeMenuCategory = cafeSeriesReaderPort.getCafeMenuCategoryNotNull(menuCategoryId)

        cafeMenuCategory.update(command)
    }

    @Transactional
    override fun deleteMenuCategory(cafeId: Long, menuCategoryId: Long) {
        cafeMenuCategoryValidator.validateTheSameCafe(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId
        )

        val cafeMenuCategory = cafeSeriesReaderPort.getCafeMenuCategoryNotNull(menuCategoryId)

        cafeRemoverPort.deleteCafeMenuCategory(cafeMenuCategory)
    }
}