import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Spring.BOOT).version(Plugins.Spring.BOOT_VERSION)
    id(Plugins.Spring.DEPENDENCY_MANAGEMENT).version(Plugins.Spring.DEPENDENCY_MANAGEMENT_VERSION).apply(false)
    kotlin(Plugins.Kotlin.JVM).version(Plugins.Kotlin.VERSION)
    kotlin(Plugins.Kotlin.PLUGIN_SPRING).version(Plugins.Kotlin.VERSION).apply(false)
    kotlin(Plugins.Kotlin.PLUGIN_JPA).version(Plugins.Kotlin.VERSION).apply(false)
}

val bootJar: BootJar by tasks
bootJar.enabled = false

allprojects {
    group = "io.beaniejoy.dongecafe"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin(Plugins.JAVA)
        plugin(Plugins.Spring.DEPENDENCY_MANAGEMENT)
        plugin(Plugins.Spring.BOOT)

        plugin(Plugins.Kotlin.KOTLIN)
        plugin(Plugins.Kotlin.KOTLIN_SPRING)
        plugin(Plugins.Kotlin.KOTLIN_JPA)
    }

    dependencies {
        // Spring Boot Project
        implementation("${Plugins.Spring.BOOT}:spring-boot-starter-data-jpa")
        implementation("${Plugins.Spring.BOOT}:spring-boot-starter-web")
        implementation("${Plugins.Spring.BOOT}:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // DB
        runtimeOnly("mysql:mysql-connector-java") // MySQL
        runtimeOnly("com.h2database:h2") // H2
        implementation("org.flywaydb:flyway-core:7.15.0") // flyway

        // Logging
        implementation("io.github.microutils:kotlin-logging:2.1.21")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
    }

    tasks.withType<KotlinCompile> {
        println("Configuring $name in project ${project.name}...")
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}