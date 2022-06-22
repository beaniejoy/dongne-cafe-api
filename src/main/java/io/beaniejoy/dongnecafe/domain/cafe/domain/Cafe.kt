package io.beaniejoy.dongnecafe.domain.cafe.domain

import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity(name = "cafe")
class Cafe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "name")
    var name: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "phone_number")
    var phoneNumber: String,

    @Column(name = "total_rate")
    val totalRate: Double,

    @Column(name = "description")
    var description: String,

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    val cafeMenuList: MutableList<CafeMenu>,

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    val cafeImageList: MutableList<CafeImage>
) : BaseTimeEntity() {
    fun updateInfo(name: String, address: String, phoneNumber: String, description: String) {
        this.name = name
        this.address = address
        this.phoneNumber = phoneNumber
        this.description = description
    }
}