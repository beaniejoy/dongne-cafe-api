//package io.beaniejoy.dongnecafe.domain.cafe.repository
//
//import io.beaniejoy.dongnecafe.common.config.AuditingConfig
//import io.beaniejoy.dongnecafe.cafe.entity.Cafe
//import io.beaniejoy.dongnecafe.cafe.repository.CafeRepository
//import io.beaniejoy.dongnecafe.utils.CafeTestUtils
//import mu.KLogging
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Disabled
//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.context.annotation.FilterType
//import org.springframework.data.repository.Repository
//import org.springframework.data.repository.findByIdOrNull
//
//@Disabled
//@DataJpaTest(
//    includeFilters = [
//        ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [AuditingConfig::class])
//    ]
//)
//internal class CafeRepositoryTest {
//    companion object : KLogging()
//
//    @Autowired
//    lateinit var cafeRepository: CafeRepository
//
//    @Test
//    @DisplayName("[JPA] 신규 Cafe save 테스트")
//    fun save_cafe_test() {
//        val cafeRequestDto = CafeTestUtils.createCafeRegisterRequest()
//        val cafe = cafeRequestDto.let {
//            Cafe.createEntity(
//                name = it.name!!,
//                address = it.address!!,
//                phoneNumber = it.phoneNumber!!,
//                description = it.description!!
//            )
//        }
//
//        val savedCafe = cafeRepository.save(cafe)
//
//        assertEquals(1L, savedCafe.id)
//        assertEquals(cafeRequestDto.name, savedCafe.name)
//        assertEquals(cafeRequestDto.address, savedCafe.address)
//        assertEquals(cafeRequestDto.phoneNumber, savedCafe.phoneNumber)
//        assertEquals(cafeRequestDto.description, savedCafe.description)
//    }
//
//    @Test
//    @DisplayName("[JPA] 기존 Cafe 정보 update 테스트")
//    fun update_cafe_test() {
//        // TODO 테스트용 카페 데이터 주입 구성하기
//        val cafeRequestDto = CafeTestUtils.createCafeRegisterRequest()
//        val cafe = cafeRequestDto.let {
//            Cafe.createEntity(
//                name = it.name!!,
//                address = it.address!!,
//                phoneNumber = it.phoneNumber!!,
//                description = it.description!!
//            )
//        }
//
//        val savedId = cafeRepository.save(cafe).id
//
//        val findCafe = cafeRepository.findByIdOrNull(savedId)
//
//        val updatedName = "update cafe name"
//        val updatedAddress = "update cafe address"
//        val updatedPhoneNumber = "01011112222"
//        val updatedDescription = "update description"
//
//        findCafe!!.updateInfo(
//            name = updatedName,
//            address = updatedAddress,
//            phoneNumber = updatedPhoneNumber,
//            description = updatedDescription
//        )
//
//        val updatedCafe = cafeRepository.findByIdOrNull(savedId)
//
//        assertEquals(updatedName, updatedCafe!!.name)
//        assertEquals(updatedAddress, updatedCafe.address)
//        assertEquals(updatedPhoneNumber, updatedCafe.phoneNumber)
//        assertEquals(updatedDescription, updatedCafe.description)
//    }
//
//    @Test
//    fun select_cafe_test_data() {
//        val cafes = cafeRepository.findAll()
//        cafes.forEach {
//            logger.info { "cafe id=[${it.id}], name=[${it.name}]" }
//        }
//    }
//}
