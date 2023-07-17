package io.beaniejoy.dongnecafe.db.cafe.adapter

import io.beaniejoy.dongnecafe.db.cafe.repository.*
import io.beaniejoy.dongnecafe.domain.cafe.entity.*
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeStorePort
import org.springframework.stereotype.Component

@Component
class CafeStoreAdapter(
    private val cafeRepository: CafeRepository,
    private val cafeMenuCategoryRepository: CafeMenuCategoryRepository,
    private val cafeMenuRepository: CafeMenuRepository,
    private val menuOptionRepository: MenuOptionRepository,
    private val optionDetailRepository: OptionDetailRepository
) : CafeStorePort {
    override fun store(cafe: Cafe): Cafe {
        return cafeRepository.save(cafe)
    }

    override fun store(cafeMenuCategory: CafeMenuCategory): CafeMenuCategory {
        return cafeMenuCategoryRepository.save(cafeMenuCategory)
    }

    override fun store(cafeMenu: CafeMenu): CafeMenu {
        return cafeMenuRepository.save(cafeMenu)
    }

    override fun store(menuOption: MenuOption): MenuOption {
        return menuOptionRepository.save(menuOption)
    }

    override fun store(optionDetail: OptionDetail): OptionDetail {
        return optionDetailRepository.save(optionDetail)
    }
}
