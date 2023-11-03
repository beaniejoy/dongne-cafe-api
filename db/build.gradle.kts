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

flyway {
    val flywayConfig = FlywayConfig.init()

    baselineDescription = "Start Flyway Migration!"
    url = flywayConfig.getConnection().url
    user = flywayConfig.getConnection().username
    password = flywayConfig.getConnection().password
    baselineOnMigrate = true
    baselineVersion = "000"
    locations = flywayConfig.getLocations()
    configFiles = flywayConfig.getConfigFiles()
}
