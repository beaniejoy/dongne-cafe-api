package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuRegisterRequest
import javax.persistence.*

@Entity
@Table(name = "cafe")
class Cafe protected constructor(
    name: String,
    address: String,
    phoneNumber: String,
    description: String,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id", nullable = false)
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
    val cafeMenus: MutableList<CafeMenu> = arrayListOf()

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val cafeImages: MutableList<CafeImage> = arrayListOf()

    companion object {
        fun createCafe(
            name: String,
            address: String,
            phoneNumber: String,
            description: String,
            cafeMenuRequests: List<CafeMenuRegisterRequest>,
        ): Cafe {
            val cafeMenuEntities = cafeMenuRequests.map { cafeMenuRequestDto ->
                CafeMenu.createCafeMenu(
                    name = cafeMenuRequestDto.name!!,
                    price = cafeMenuRequestDto.price,
                    menuOptionRequests = cafeMenuRequestDto.menuOptions
                )
            }

            return Cafe(
                name = name,
                address = address,
                phoneNumber = phoneNumber,
                description = description
            ).apply {
                cafeMenuEntities.forEach { this.addCafeMenu(it) }
            }
        }
    }

    fun addCafeMenu(cafeMenu: CafeMenu) {
        this.cafeMenus.add(cafeMenu)
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