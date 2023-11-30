package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeInfo
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CafeService {

    fun registerCafe(command: CafeCommand.RegisterCafe): CafeInfo.RegisteredCafe

    fun searchCafes(param: CafeQuery.SearchCafesParam, pageable: Pageable): Page<CafeInfo.CafeSearchInfo>

    fun getDetailedCafe(name: String): CafeInfo.CafeDetailInfo

    fun updateCafe(id: Long, command: CafeCommand.UpdateCafe)
}
