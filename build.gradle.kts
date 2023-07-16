import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import plugin.BuildLifecyclePlugin
import task.test.TestContainer
import task.test.TestLoggingUtils
import task.test.TestSummary

plugins {
    id(Plugins.Spring.BOOT).version(Version.Spring.BOOT)
    id(Plugins.Spring.DEPENDENCY_MANAGEMENT).version(Version.Spring.DEPENDENCY_MANAGEMENT).apply(false)
    kotlin(Plugins.Kotlin.JVM).version(Version.KOTLIN)
    kotlin(Plugins.Kotlin.PLUGIN_SPRING).version(Version.KOTLIN).apply(false)
    kotlin(Plugins.Kotlin.PLUGIN_JPA).version(Version.KOTLIN).apply(false)
    kotlin(Plugins.Kotlin.KAPT).version(Version.KOTLIN)

    id(Plugins.KTLINT).version(Version.KTLINT)
}

java {
    sourceCompatibility = Version.JAVA
    targetCompatibility = Version.JAVA
}

allprojects {
    group = "io.beaniejoy.dongecafe"
//    version = Version.PROJECT_VERSION

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
//        plugin(Plugins.Kotlin.KOTLIN_JPA)
        plugin(Plugins.Kotlin.KOTLIN_KAPT)
    }

    dependencies {
        // Spring Boot Project
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // Logging
        implementation("io.github.microutils:kotlin-logging:${Version.Deps.KOTLIN_LOGGING}")

        // mapstruct for dto mapping (https://mapstruct.org/documentation/reference-guide/)
        implementation("org.mapstruct:mapstruct:${Version.Deps.MAPSTRUCT}")
        kapt("org.mapstruct:mapstruct-processor:${Version.Deps.MAPSTRUCT}")
        kaptTest("org.mapstruct:mapstruct-processor:${Version.Deps.MAPSTRUCT}")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = Version.JAVA.toString()
        }
    }

    // gradle test logging
    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            events = setOf(
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_ERROR
            )

            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }

//        ignoreFailures = true

        addTestListener(object : TestListener {
            override fun beforeSuite(desc: TestDescriptor) {}

            // handling after all test finished
            override fun afterSuite(desc: TestDescriptor, result: TestResult) {
                if (desc.parent != null) return

                val summary = TestSummary(
                    projectName = project.name,
                    taskName = name,
                    result = result
                )

                TestContainer.testResults = summary
            }

            override fun beforeTest(desc: TestDescriptor) {}

            // handling after each test finished
            override fun afterTest(desc: TestDescriptor, result: TestResult) {
                TestLoggingUtils.printEachResult(desc, result)
            }
        })
    }

    // for logging when build finished
    apply<BuildLifecyclePlugin>()
}