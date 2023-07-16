import org.gradle.api.JavaVersion

object Version {
    const val PROJECT_VERSION = "0.0.1-SNAPSHOT"
    const val KOTLIN = "1.8.21"

    val JAVA = JavaVersion.VERSION_17

    object Spring {
        const val BOOT = "2.7.0"
        const val DEPENDENCY_MANAGEMENT = "1.0.14.RELEASE"
    }

    object Deps {
        const val KOTLIN_LOGGING = "3.0.4"
        const val JWT = "0.11.5"

        const val MYSQL = "8.0.32"

        const val MAPSTRUCT = "1.5.5.Final"
    }

    object KtLint {
        const val PLUGIN = "11.5.0"
        const val PINTEREST = "0.49.1"
    }
}
