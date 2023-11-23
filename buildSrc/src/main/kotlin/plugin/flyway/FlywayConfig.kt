package plugin.flyway

import build.BuildProfile

class FlywayConfig(
    private val activeProfile: BuildProfile
) {
    companion object {
        private const val MIGRATION_DIR = "migration"
        private const val SEED_DIR = "seed"
        private const val MIGRATION_PATH = "src/main/resources/db"

        fun init(): FlywayConfig {
            return FlywayConfig(activeProfile = BuildProfile.init())
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
}
