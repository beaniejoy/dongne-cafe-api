//package io.beaniejoy.dongnecafe.domain.cafe.service
//
//import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
//import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
//import io.beaniejoy.dongnecafe.cafe.entity.Cafe
//import io.beaniejoy.dongnecafe.cafe.repository.CafeRepository
//import io.beaniejoy.dongnecafe.utils.CafeTestUtils
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.junit.jupiter.MockitoExtension
//import java.util.*
//
//// TODO: BDD 스타일로 리팩토링 해볼 것(추후 mockK도 사용해보자)
//@ExtendWith(MockitoExtension::class)
//@TestMethodOrder(MethodOrderer.DisplayName::class)
//internal class CafeServiceTest {
//    @InjectMocks
//    lateinit var mockCafeService: CafeService
//
//    @Mock
//    lateinit var mockCafeRepository: CafeRepository
//
//    @Disabled
//    @Test
//    @DisplayName("카페 신규 생성 테스트")
//    fun create_cafe_test() {
//        // given
//        val (name, address, phoneNumber, description, cafeMenus) = CafeTestUtils.createCafeRegisterRequest()
//        val savedMockCafeId = 100L
//
//        `when`(mockCafeRepository.findByName(name!!)).thenReturn(null)
//        `when`(mockCafeRepository.save(any(Cafe::class.java))).thenAnswer {
//            CafeTestUtils.injectCafeId(it.getArgument(0), savedMockCafeId)
//        }
//
//        // when
//        val savedCafe = mockCafeService.createNew(
//            name = name,
//            address = address!!,
//            phoneNumber = phoneNumber!!,
//            description = description!!,
//            cafeMenuCategoryRegisterRequests = cafeMenus
//        )
//
//        // then
//        verify(mockCafeRepository).findByName(name) // TODO eq 에러 발생 이유
//        verify(mockCafeRepository).save(any(Cafe::class.java))
//
//        assertEquals(savedCafe, savedMockCafeId)
//    }
//
//    @Disabled
//    @Test
//    @DisplayName("카페 신규 생성시 이미 존재하는 카페 예외 발생 테스트")
//    fun fail_create_cafe_when_existed() {
//        // given
//        val (name, address, phoneNumber, description, cafeMenus) = CafeTestUtils.createCafeRegisterRequest()
//        val cafe = Cafe.createEntity(
//            name = name!!,
//            address = address!!,
//            phoneNumber = phoneNumber!!,
//            description = description!!
//        )
//
//        `when`(mockCafeRepository.findByName(name)).thenReturn(cafe)
//
//        // then
//        val exception = assertThrows<BusinessException> {
//            // when
//            mockCafeService.createNew(
//                name = name,
//                address = address,
//                phoneNumber = phoneNumber,
//                description = description,
//                cafeMenuCategoryRegisterRequests = cafeMenus
//            )
//        }
//        verify(mockCafeRepository).findByName(name)
//        assertEquals(ErrorCode.CAFE_EXISTED, exception.errorCode)
//    }
//
//    @Disabled
//    @Test
//    @DisplayName("카페 정보 변경 테스트")
//    fun update_cafe_test() {
//        // given
//        val (name, address, phoneNumber, description, cafeMenus) = CafeTestUtils.createCafeRegisterRequest()
////        val cafe = Cafe.createCafe(
////            name = name!!,
////            address = address!!,
////            phoneNumber = phoneNumber!!,
////            description = description!!,
////            cafeMenuRequests = cafeMenus
////        )
//        val cafeId = 50L
//
//        // TODO 'findByIdOrNull'은 kotlin test 라이브러리 필요한 듯
//        val mockCafe = mock(Cafe::class.java)
//        `when`(mockCafeRepository.findById(cafeId)).thenReturn(Optional.of(mockCafe))
//
//        doNothing().`when`(mockCafe).updateInfo(
//            name = anyString(),
//            address = anyString(),
//            phoneNumber = anyString(),
//            description = anyString()
//        )
//
//        // when
//        mockCafeService.updateInfo(
//            id = cafeId,
//            name = "updated_name",
//            address = "updated_address",
//            phoneNumber = "updated_phoneNumber",
//            description = "updated_desc"
//        )
//
//        // then
//        verify(mockCafeRepository).findById(eq(cafeId))
//        verify(mockCafe).updateInfo(
//            name = "updated_name",
//            address = "updated_address",
//            phoneNumber = "updated_phoneNumber",
//            description = "updated_desc"
//        )
//    }
//
//    @Disabled
//    @Test
//    @DisplayName("카페 정보 변경시 존재하지 않는 카페 예외 발생 테스트")
//    fun fail_update_cafe_when_not_found() {
//        // given
//        val cafeId = 50L
//
//        `when`(mockCafeRepository.findById(cafeId)).thenReturn(Optional.empty())
//
//        val exception = assertThrows<BusinessException> {
//            mockCafeService.updateInfo(
//                id = cafeId,
//                name = "",
//                address = "",
//                phoneNumber = "",
//                description = "",
//            )
//        }
//
//        assertEquals(ErrorCode.CAFE_NOT_FOUND, exception.errorCode)
//    }
//}