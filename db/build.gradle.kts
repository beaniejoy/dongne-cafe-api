import org.springframework.boot.gradle.tasks.bundling.BootJar
import plugin.flyway.FlywayConfig

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        // flyway mysql 8 version gradle 실행 전용
        // (이거 없으면 gradle flyway task 수행시 에러 발생)
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

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${Version.Deps.QUERYDSL}:jakarta")
    kapt("com.querydsl:querydsl-apt:${Version.Deps.QUERYDSL}:jakarta")

    // DB
    runtimeOnly("mysql:mysql-connector-java:${Version.Deps.MYSQL}") // MySQL
    runtimeOnly("com.h2database:h2") // H2
}

flyway {
    val flywayConfig = FlywayConfig.init()

    baselineDescription = "Start Flyway Migration!"
    baselineOnMigrate = true
    baselineVersion = "000"
    locations = flywayConfig.getLocations()
    configFiles = flywayConfig.getConfigFiles()
}
