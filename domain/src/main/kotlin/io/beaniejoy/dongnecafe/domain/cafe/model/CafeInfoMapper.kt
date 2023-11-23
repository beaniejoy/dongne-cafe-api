package io.beaniejoy.dongnecafe.domain.cafe.model

import io.beaniejoy.dongnecafe.domain.cafe.entity.*
import io.beaniejoy.dongnecafe.domain.cafe.entity.image.CafeImage
import io.beaniejoy.dongnecafe.domain.cafe.entity.image.CafeMenuCategoryImage
import io.beaniejoy.dongnecafe.domain.cafe.entity.image.CafeMenuImage
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface CafeInfoMapper {
    // #### [START] response of registered entity ####
    @Mapping(source = "cafe.id", target = "cafeId")
    fun of(cafe: Cafe): CafeInfo.RegisteredCafe

    @Mapping(source = "cafeMenuCategory.id", target = "menuCategoryId")
    fun of(cafeMenuCategory: CafeMenuCategory): CafeInfo.RegisteredCafeMenuCategory

    @Mapping(source = "cafeMenu.id", target = "cafeMenuId")
    fun of(cafeMenu: CafeMenu, menuOptions: List<MenuOption>): CafeInfo.RegisteredCafeMenu

    @Mapping(source = "menuOption.id", target = "menuOptionId")
    fun of(menuOption: MenuOption): CafeInfo.RegisteredMenuOption

    @Mapping(source = "optionDetail.id", target = "optionDetailId")
    fun of(optionDetail: OptionDetail): CafeInfo.RegisteredOptionDetail
    // #### [END] response of registered entity ####

    @Mapping(source = "cafe.id", target = "cafeId")
    fun cafeSearchInfoOf(cafe: Cafe, cafeImages: List<CafeImage>): CafeInfo.CafeSearchInfo

    @Mapping(source = "cafeImage.id", target = "imageId")
    fun cafeImageSearchInfoOf(cafeImage: CafeImage): CafeInfo.ImageInfo

    @Mapping(source = "cafe.id", target = "cafeId")
    fun cafeDetailedInfoOf(cafe: Cafe): CafeInfo.CafeDetailedInfo

    @Mapping(source = "cafeMenuCategory.id", target = "menuCategoryId")
    fun cafeMenuCategoryInfoOf(cafeMenuCategory: CafeMenuCategory): CafeInfo.CafeMenuCategoryInfo

    @Mapping(source = "cafeMenu.id", target = "cafeMenuId")
    fun cafeMenuInfoOf(cafeMenu: CafeMenu): CafeInfo.CafeMenuInfo

    @Mapping(source = "cafeMenuCategoryImage.id", target = "imageId")
    fun cafeMenuCategoryImageInfoOf(cafeMenuCategoryImage: CafeMenuCategoryImage): CafeInfo.ImageInfo

    @Mapping(source = "cafeMenuImage.id", target = "imageId")
    fun cafeMenuImageInfoOf(cafeMenuImage: CafeMenuImage): CafeInfo.ImageInfo

    @Mapping(source = "menuOption.id", target = "menuOptionId")
    fun menuOptionInfoOf(menuOption: MenuOption): CafeInfo.MenuOptionInfo

    @Mapping(source = "optionDetail.id", target = "optionDetailId")
    fun optionDetailInfoOf(optionDetail: OptionDetail): CafeInfo.OptionDetailInfo
}
