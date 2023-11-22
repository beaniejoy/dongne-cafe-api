package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.domain.cafe.entity.image.CafeImage
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import javax.persistence.*

@Entity
@Table(name = "cafes")
class Cafe protected constructor(
    name: String,
    address: String,
    phoneNumber: String,
    description: String?
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id", nullable = false)
    val id: Long = 0L

    @Column(name = "name", nullable = false, unique = true)
    var name: String = name.replace(WHITE_SPACE, INVALID_NAME_CHARACTER)
        protected set

    @Column(name = "address", nullable = false)
    var address: String = address
        protected set

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = phoneNumber
        protected set

    @Column(name = "total_rate", nullable = false)
    val totalRate: Double = 0.0

    @Column(name = "description", length = 255)
    var description: String? = description
        protected set

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    val cafeMenuCategories: MutableList<CafeMenuCategory> = arrayListOf()

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    val cafeImages: MutableList<CafeImage> = arrayListOf()

    init {
        if (name.isBlank() || name.contains(INVALID_NAME_CHARACTER)) {
            throw BusinessException(errorCode = ErrorCode.CAFE_INVALID_REQUEST)
        }
    }

    companion object {
        const val INVALID_NAME_CHARACTER = '-'
        private const val WHITE_SPACE = ' '

        fun createEntity(command: CafeCommand.RegisterCafe): Cafe {
            return Cafe(
                name = command.name,
                address = command.address,
                phoneNumber = command.phoneNumber,
                description = command.description
            )
        }
    }

    fun update(command: CafeCommand.UpdateCafe) {
        this.name = command.name
        this.address = command.address
        this.phoneNumber = command.phoneNumber
        this.description = command.description
    }

    fun checkTheSameAs(other: Cafe?) {
        if (other == null || this !== other) {
            throw BusinessException(ErrorCode.CAFE_INVALID_REQUEST)
        }
    }
}
