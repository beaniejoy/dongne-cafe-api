package io.beaniejoy.dongnecafe.cafe.adapter

import io.beaniejoy.dongnecafe.cafe.entity.*
import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.cafe.persistence.CafeSeriesStorePort
import io.beaniejoy.dongnecafe.cafe.persistence.CafeStorePort
import org.springframework.stereotype.Component

@Component
class CafeSeriesStoreAdapter(
    private val cafeStorePort: CafeStorePort
) : CafeSeriesStorePort {
    /**
     * store all data series of cafe
     * (CafeMenuCategory > CafeMenu > MenuOption > OptionDetail)
     */
    override fun storeMenuCategorySeries(
        cafe: Cafe,
        registerCafe: CafeCommand.RegisterCafe
    ): List<CafeMenuCategory> {
        return registerCafe.cafeMenuCategories.map { registerCafeMenuCategory ->
            val cafeMenuCategory = cafeStorePort.store(
                CafeMenuCategory.createEntity(registerCafeMenuCategory).apply {
                    this.updateCafe(cafe)
                }
            )

            registerCafeMenuCategory.cafeMenus.forEach { registerCafeMenu ->
                val cafeMenu = cafeStorePort.store(
                    CafeMenu.createEntity(registerCafeMenu).apply {
                        this.updateCafeMenuCategory(cafeMenuCategory)
                    }
                )

                registerCafeMenu.menuOptions.forEach { registerMenuOption ->
                    val menuOption = cafeStorePort.store(
                        MenuOption.createEntity(registerMenuOption).apply {
                            this.updateCafeMenu(cafeMenu)
                        }
                    )

                    registerMenuOption.optionDetails.forEach { registerOptionDetail ->
                        cafeStorePort.store(
                            OptionDetail.createEntity(registerOptionDetail).apply {
                                this.updateMenuOption(menuOption)
                            }
                        )
                    }
                }
            }

            cafeMenuCategory
        }
    }
}