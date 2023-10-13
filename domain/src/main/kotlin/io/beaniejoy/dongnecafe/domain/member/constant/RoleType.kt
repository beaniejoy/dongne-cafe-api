package io.beaniejoy.dongnecafe.domain.member.constant

enum class RoleType(val roleName: String) {
    ROLE_ADMIN("어드민 관리자"),
    ROLE_USER("일반 사용자"),
    ROLE_OWNER("카페 관리자"),
    ROLE_MONITORING("모니터링 수행자")
    ;

    companion object {
        const val ROLE_PREFIX = "ROLE_"
    }

    fun securityRoleName(): String {
        return this.name.removePrefix(ROLE_PREFIX)
    }
}
