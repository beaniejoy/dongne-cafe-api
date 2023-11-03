class FlywayConfig(
    private val activeProfile: BuildProfile,
    private val connection: FlywayConnection
) {
    companion object {
        private const val MIGRATION_DIR = "migration"
        private const val SEED_DIR = "seed"
        private const val MIGRATION_PATH = "src/main/resources/db"

        fun init(): FlywayConfig {
            return FlywayConfig(
                activeProfile = BuildProfile.init(),
                connection = FlywayConnection.init()
            )
        }
    }

    fun getConfigFiles(): Array<String> {
        return listOf("flyway-${activeProfile.getProfileName()}.conf")
            .map { "$MIGRATION_PATH/$it" }
            .toTypedArray()
    }

    fun getLocations(): Array<String> {
        val locationDirs = if (activeProfile.isDev()) listOf(MIGRATION_DIR, SEED_DIR) else listOf(MIGRATION_DIR)

        return locationDirs.map {
            "filesystem:$MIGRATION_PATH/$it"
        }.toTypedArray()
    }

    fun getConnection(): FlywayConnection {
        return connection
    }

    class FlywayConnection private constructor(
        val url: String?,
        val username: String?,
        val password: String?
    ) {
        companion object {
            private const val URL_KEY = "flyway.url"
            private const val URL_USERNAME = "flyway.username"
            private const val URL_PASSWORD = "flyway.password"

            fun init(): FlywayConnection {
                return FlywayConnection(
                    url = System.getProperty(URL_KEY) ?: null,
                    username = System.getProperty(URL_USERNAME) ?: null,
                    password = System.getProperty(URL_PASSWORD) ?: null
                )
            }
        }
    }
}
