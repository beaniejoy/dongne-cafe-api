import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.Spring.BOOT) version Plugins.Spring.BOOT_VERSION
    id(Plugins.Spring.DEPENDENCY_MANAGEMENT) version Plugins.Spring.DEPENDENCY_MANAGEMENT_VERSION
    kotlin(Plugins.Kotlin.JVM) version Plugins.Kotlin.VERSION
    kotlin(Plugins.Kotlin.PLUGIN_SPRING) version Plugins.Kotlin.VERSION apply false    // TODO: apply false what?
    kotlin(Plugins.Kotlin.PLUGIN_JPA) version Plugins.Kotlin.VERSION apply false
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    group = "io.beaniejoy.dongecafe"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = Plugins.JAVA)

    apply(plugin = Plugins.Spring.DEPENDENCY_MANAGEMENT)
    apply(plugin = Plugins.Spring.BOOT)
    apply(plugin = "org.jetbrains.kotlin.plugin.spring") // TODO: 알아보기

    apply(plugin = Plugins.Kotlin.KOTLIN)
    apply(plugin = Plugins.Kotlin.KOTLIN_SPRING)
    apply(plugin = Plugins.Kotlin.KOTLIN_JPA)

    repositories {
        mavenCentral()
    }

//    configurations {
//        all {
//            // log4j2 적용을 위해 기존 spring boot에서 제공하는 logging exclude
//            exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
//        }
//    }

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
        // log4j2
        // implementation("org.springframework.boot:spring-boot-starter-log4j2")
        // testImplementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("io.github.microutils:kotlin-logging:2.1.21")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}