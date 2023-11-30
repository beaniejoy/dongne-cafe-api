package io.beaniejoy.dongnecafe.db.cafe.repository.custom

import com.querydsl.jpa.impl.JPAQueryFactory
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.entity.QCafe.cafe
import io.beaniejoy.dongnecafe.domain.cafe.entity.QCafeMenuCategory.cafeMenuCategory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class CafeQueryRepositoryImpl(
    @Qualifier("defaultJpaQueryFactory")
    private val jpaQueryFactory: JPAQueryFactory
) : CafeQueryRepository {
    override fun findDetailFetchJoinByName(name: String): Cafe? {
        return jpaQueryFactory
            .selectFrom(cafe)
            .join(cafe.cafeMenuCategories, cafeMenuCategory).fetchJoin()
            .where(cafe.name.eq(name))
            .fetchOne()
    }
}
