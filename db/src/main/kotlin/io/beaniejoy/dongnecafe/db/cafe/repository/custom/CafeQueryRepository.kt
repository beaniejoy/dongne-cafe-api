package io.beaniejoy.dongnecafe.db.cafe.repository.custom

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe

interface CafeQueryRepository {
    fun findDetailFetchJoinByName(name: String): Cafe?
}
