package io.beaniejoy.dongnecafe.domain.cafe.repository

import io.beaniejoy.dongnecafe.common.config.AuditingConfig
import io.beaniejoy.dongnecafe.common.entity.BaseEntityAuditorAware
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.utils.CafeTestUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@DataJpaTest(
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = [AuditingConfig::class]
        ),
        ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = [BaseEntityAuditorAware::class]
        )
    ]
)
internal class CafeRepositoryTest {
    @Autowired
    lateinit var cafeRepository: CafeRepository

    @Test
    @DisplayName("[JPA] 신규 Cafe 저장 테스트")
    fun saveTest() {
        val cafeRequestDto = CafeTestUtils.createCafeRequestDto()
        val cafe = cafeRequestDto.let {
            Cafe.createCafe(
                name = it.name!!,
                address = it.address!!,
                phoneNumber = it.phoneNumber!!,
                description = it.description!!,
                cafeMenuRequestList = it.cafeMenuList
            )
        }

        val savedCafe = cafeRepository.save(cafe)

        assertEquals(1L, savedCafe.id)
        CafeTestUtils.assertCafeEquals(cafeRequestDto, savedCafe)
    }
}