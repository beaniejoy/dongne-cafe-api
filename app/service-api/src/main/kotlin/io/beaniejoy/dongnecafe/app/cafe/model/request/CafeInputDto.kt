package io.beaniejoy.dongnecafe.app.cafe.model.request

import java.math.BigDecimal


class CafeInputDto {
    // ### Request Body ###
    data class RegisterCafeRequest(
        val name: String?,
        val address: String?,
        val phoneNumber: String?,
        val description: String?
    )

    data class RegisterCafeMenuCategoryRequest(
        val name: String,
        val description: String
    )

    data class RegisterCafeMenuRequest(
        val name: String,
        val price: BigDecimal,
        val description: String?,
        val menuOptions: List<RegisterMenuOptionRequest> = emptyList()
    )

    data class RegisterMenuOptionRequest(
        val title: String,
        val optionDetails: List<RegisterOptionDetailRequest> = emptyList()
    )

    data class RegisterOptionDetailRequest(
        val name: String,
        val extraPrice: BigDecimal
    )

    data class UpdateCafeRequest(
        val name: String,
        val address: String,
        val phoneNumber: String,
        val description: String?
    )

    data class UpdateCafeMenuCategoryRequest(
        val name: String,
        val description: String
    )

    data class UpdateCafeMenuRequest(
        val name: String,
        val price: BigDecimal,
        val description: String?,
        val menuOptions: List<UpdateMenuOptionRequest>
    )

    data class UpdateMenuOptionRequest(
        val menuOptionId: Long,
        val title: String,
        val optionDetails: List<UpdateOptionDetailRequest> = emptyList(),
        val delete: Boolean = false
    )

    data class UpdateOptionDetailRequest(
        val optionDetailId: Long,
        val name: String,
        val extraPrice: BigDecimal,
        val delete: Boolean = false
    )

    data class BulkDeleteCafeMenusRequest(
        val cafeMenuIds: List<Long>
    )

    // ### Request Param ###
    data class SearchCafesParam(
        val name: String?,
        val address: String?
    )
}