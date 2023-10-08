package io.beaniejoy.dongnecafe.domain.member.entity

import io.beaniejoy.dongnecafe.domain.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.member.constant.RoleType
import io.beaniejoy.dongnecafe.domain.member.model.MemberCommand
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "members")
class Member(
    email: String,
    password: String,
    address: String?,
    phoneNumber: String?
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    val id: Long = 0L

    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "password", nullable = false)
    var password: String = password
        protected set

    @Column(name = "address")
    var address: String? = address
        protected set

    @Column(name = "phone_number")
    var phoneNumber: String? = phoneNumber
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    var roleType: RoleType = RoleType.ROLE_USER
        protected set

    @Column(name = "activated", nullable = false)
    var activated: Boolean = true
        protected set

    companion object {
        fun createEntity(
            command: MemberCommand.RegisterMember,
            passwordEncoder: PasswordEncoder
        ): Member {
            return Member(
                email = command.email,
                password = passwordEncoder.encode(command.password),
                address = command.address,
                phoneNumber = command.phoneNumber
            )
        }
    }
}
