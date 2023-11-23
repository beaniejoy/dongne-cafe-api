import org.gradle.api.JavaVersion

object Version {
    const val PROJECT_VERSION = "0.0.1-SNAPSHOT"

    val JAVA = JavaVersion.VERSION_17

    object Spring {
        // spring boot v3
        const val BOOT = "3.1.5"
        const val DEPENDENCY_MANAGEMENT = "1.1.3"
    }

    object Deps {
        const val KOTLIN_LOGGING = "3.0.4"
        const val JWT = "0.11.5"

        const val MYSQL = "8.0.32"

        const val MAPSTRUCT = "1.5.5.Final"

        const val FLYWAY = "9.8.3"
    }

    object KtLint {
        const val PLUGIN = "11.5.0"
        const val PINTEREST = "0.49.1"
    }
}
