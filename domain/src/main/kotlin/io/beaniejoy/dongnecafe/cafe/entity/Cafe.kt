package io.beaniejoy.dongnecafe.cafe.entity

import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "cafes")
class Cafe protected constructor(
    name: String,
    address: String,
    phoneNumber: String,
    description: String
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
    val cafeMenuCategories: MutableList<CafeMenuCategory> = arrayListOf()

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val cafeImages: MutableList<CafeImage> = arrayListOf()

    companion object {
        fun createEntity(registerCommand: CafeCommand.RegisterCafe): Cafe {
            return Cafe(
                name = registerCommand.name,
                address = registerCommand.address,
                phoneNumber = registerCommand.phoneNumber,
                description = registerCommand.description
            )
        }
    }

    fun updateEntity(updateCommand: CafeCommand.UpdateCafe) {
        this.name = updateCommand.name
        this.address = updateCommand.address
        this.phoneNumber = updateCommand.phoneNumber
        this.description = updateCommand.description
    }
}