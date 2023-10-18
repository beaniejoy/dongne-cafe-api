package io.beaniejoy.dongnecafe.common.constant

enum class AppModule {
    DEFAULT,
    COMMON,
    DOMAIN,
    DB,
    INFRA
    ;

    companion object {
        const val PREFIX_CONFIG_NAME = "application"
        private const val COMMA_FOR_CONFIG = ","

        fun getConfigNames(): String {
            return AppModule.values()
                .joinToString(COMMA_FOR_CONFIG) { it.moduleNameWithPrefix() }
        }
    }

    private fun moduleNameWithPrefix(): String {
        if (this == DEFAULT) {
            return PREFIX_CONFIG_NAME
        }

        return "$PREFIX_CONFIG_NAME-${this.name.lowercase()}"
    }
}
