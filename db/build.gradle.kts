import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-mysql:${Version.Deps.FLYWAY}")
    }
}

plugins {
    kotlin(Plugins.Kotlin.PLUGIN_JPA).version(Version.KOTLIN)
    id(Plugins.FLYWAY).version(Version.Deps.FLYWAY)
}

dependencies {
    implementation(project(SubModule.DOMAIN))
    testImplementation(testFixtures(project(SubModule.DOMAIN)))

    implementation("org.springframework.boot:spring-boot-starter-security")

    // flyway db migration
    implementation("org.flywaydb:flyway-core:${Version.Deps.FLYWAY}")
    implementation("org.flywaydb:flyway-mysql:${Version.Deps.FLYWAY}") // mysql 8 version ??

    // DB
    runtimeOnly("mysql:mysql-connector-java:${Version.Deps.MYSQL}") // MySQL
    runtimeOnly("com.h2database:h2") // H2
}

class FlywayConfig(private val activeProfile: BuildProfile) {
    companion object {
        private const val MIGRATION_DIR = "migration"
        private const val SEED_DIR = "seed"
        private const val MIGRATION_PATH = "src/main/resources/db"

        fun init(): FlywayConfig {
            return FlywayConfig(BuildProfile.init())
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

flyway {
    val flywayConfig = FlywayConfig.init()

    baselineDescription = "Start Flyway Migration!"
    baselineOnMigrate = true
    baselineVersion = "000"
    locations = flywayConfig.getLocations()
    configFiles = flywayConfig.getConfigFiles()
}
