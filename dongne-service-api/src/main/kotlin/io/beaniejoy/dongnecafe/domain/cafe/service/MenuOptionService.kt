package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionUpdateRequest
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
    fun bulkUpdate(resources: List<MenuOptionUpdateRequest>) {
        resources.forEach {
            val menuOption = menuOptionRepository.findByIdOrNull(it.menuOptionId)
                ?: throw BusinessException(ErrorCode.MENU_OPTION_NOT_FOUND)

            if (it.isDelete) {
                menuOptionRepository.delete(menuOption)
                return@forEach
            }

            menuOption.updateInfo(it.title)

            optionDetailService.bulkUpdate(it.optionDetailList)
        }
    }
}