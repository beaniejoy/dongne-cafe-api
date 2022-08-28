package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuRepository
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeRepository
import io.beaniejoy.dongnecafe.domain.cafe.utils.CafeTestUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
internal class CafeMenuServiceTest {
    @Autowired
    lateinit var cafeMenuRepository: CafeMenuRepository
    @Autowired
    lateinit var cafeRepository: CafeRepository
    @Autowired
    lateinit var cafeMenuService: CafeMenuService

    @BeforeEach
    fun setup() {
        val cafeRegisterRequest = CafeTestUtils.createCafeRegisterRequest()

        cafeRepository.save(
            Cafe.createCafe(
                name = cafeRegisterRequest.name!!,
                address = cafeRegisterRequest.address!!,
                phoneNumber = cafeRegisterRequest.phoneNumber!!,
                description = cafeRegisterRequest.description!!,
                cafeMenuRequestList = cafeRegisterRequest.cafeMenuList
            )
        )
    }

    @Test
    @Transactional
    @Rollback(false)
    fun updateTest() {
        val cafeMenu = cafeRepository.findByName("beanie_cafe")!!.cafeMenuList[0]

        cafeMenuService.updateInfoAndBulkUpdate(
            menuId = cafeMenu.id,
            cafeId = cafeMenu.cafe!!.id,
            resource = CafeMenuUpdateRequest(name = "updated_name", price = cafeMenu.price)
        )
    }

    @Test
    @Transactional
    fun deleteTest() {
        cafeMenuRepository.deleteById(100L)
    }
}