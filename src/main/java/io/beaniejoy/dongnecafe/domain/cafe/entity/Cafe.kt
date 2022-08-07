package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseTimeEntity
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.CafeMenuInfoRequestDto
import javax.persistence.*

@Entity
@Table(name = "cafe")
class Cafe protected constructor(
    name: String,
    address: String,
    phoneNumber: String,
    description: String,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "address", nullable = false)
    var address: String = address
        protected set

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = phoneNumber
        protected set

    @Column(name = "total_rate", nullable = false)
    val totalRate: Double = 0.0

    @Column(name = "description", nullable = false)
    var description: String = description
        protected set

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val cafeMenuList: MutableList<CafeMenu> = arrayListOf()

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val cafeImageList: MutableList<CafeImage> = arrayListOf()

    companion object {
        fun createCafe(
            name: String,
            address: String,
            phoneNumber: String,
            description: String,
            cafeMenuRequestList: List<CafeMenuInfoRequestDto>,
        ): Cafe {
            val cafeMenuEntityList = cafeMenuRequestList.map { cafeMenuRequestDto ->
                CafeMenu.createCafeMenu(
                    name = cafeMenuRequestDto.name!!,
                    price = cafeMenuRequestDto.price,
                    menuOptionRequestList = cafeMenuRequestDto.menuOptionList
                )
            }

            return Cafe(
                name = name,
                address = address,
                phoneNumber = phoneNumber,
                description = description
            ).apply {
                cafeMenuEntityList.forEach { this.addCafeMenu(it) }
            }
        }
    }

    fun addCafeMenu(cafeMenu: CafeMenu) {
        this.cafeMenuList.add(cafeMenu)
        cafeMenu.updateCafe(this)
    }

    fun updateInfo(
        name: String,
        address: String,
        phoneNumber: String,
        description: String,
    ) {
        this.name = name
        this.address = address
        this.phoneNumber = phoneNumber
        this.description = description
    }
}