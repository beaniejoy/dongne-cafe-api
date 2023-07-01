package io.beaniejoy.dongnecafe.cafe.model

import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import java.math.BigDecimal

class CafeCommand {
    data class RegisterCafe(
        val name: String,
        val address: String,
        val phoneNumber: String,
        val description: String?
    )

    data class RegisterCafeMenuCategory(
        val name: String,
        val description: String?
    )

    data class RegisterCafeMenu(
        val name: String,
        val price: BigDecimal,
        val description: String?,
        val menuOptions: List<RegisterMenuOption> = arrayListOf()
    ) {
        companion object {
            fun of(resource: CafeSeriesCommand.BulkUpdateCafeMenu): RegisterCafeMenu {
                require(resource.name != null && resource.price != null) {
                    throw BusinessException(ErrorCode.CAFE_MENU_INVALID_REQUEST)
                }

                return RegisterCafeMenu(
                    name = resource.name,
                    price = resource.price,
                    description = null
                )
            }
        }
    }

    data class RegisterMenuOption(
        val title: String,
        val optionDetails: List<RegisterOptionDetail> = arrayListOf()
    )

    data class RegisterOptionDetail(
        val name: String,
        val extraPrice: BigDecimal
    )

    data class UpdateCafe(
        val name: String,
        val address: String,
        val phoneNumber: String,
        val description: String?
    )

    data class UpdateCafeMenuCategory(
        val name: String,
        val description: String
    )

    data class UpdateCafeMenu(
        val name: String,
        val price: BigDecimal,
        val description: String?,
        val menuOptions: List<UpdateMenuOption>
    )

    data class UpdateMenuOption(
        val menuOptionId: Long,
        val title: String,
        val optionDetails: List<UpdateOptionDetail> = emptyList(),
        val delete: Boolean = false
    )

    data class UpdateOptionDetail(
        val optionDetailId: Long,
        val name: String,
        val extraPrice: BigDecimal,
        val delete: Boolean = false
    )
}