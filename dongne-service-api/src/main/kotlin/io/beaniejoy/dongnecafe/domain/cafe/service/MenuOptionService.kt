package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption
import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.OptionDetailRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.repository.MenuOptionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MenuOptionService(
    private val menuOptionRepository: MenuOptionRepository,
    private val optionDetailService: OptionDetailService
) {
    fun createNewOfMenu(
        title: String,
        optionDetailRegisterRequests: List<OptionDetailRegisterRequest>,
        cafeMenu: CafeMenu
    ): MenuOption {
        val menuOption = MenuOption.createEntity(title).apply {
            this.updateCafeMenu(cafeMenu)
        }

        val savedMenuOption = menuOptionRepository.save(menuOption)

        optionDetailRegisterRequests.forEach {
            optionDetailService.createNewOfMenuOption(
                name = it.name,
                extraPrice = it.extraPrice,
                savedMenuOption
            )
        }

        return savedMenuOption
    }

    fun bulkUpdate(resources: List<MenuOptionUpdateRequest>) {
        resources.forEach {
            val menuOption = menuOptionRepository.findByIdOrNull(it.menuOptionId)
                ?: throw BusinessException(ErrorCode.MENU_OPTION_NOT_FOUND)

            if (it.isDelete) {
                menuOptionRepository.delete(menuOption)
                return@forEach
            }

            menuOption.updateInfo(it.title)

            optionDetailService.bulkUpdate(it.optionDetails)
        }
    }
}