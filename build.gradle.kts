import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Spring.boot).version(Version.Spring.boot)
    id(Plugins.Spring.dependencyManagement).version(Version.Spring.dependencyManagement).apply(false)
    kotlin(Plugins.Kotlin.jvm).version(Version.kotlin)
    kotlin(Plugins.Kotlin.pluginSpring).version(Version.kotlin).apply(false)
    kotlin(Plugins.Kotlin.pluginJpa).version(Version.kotlin).apply(false)
}

val bootJar: BootJar by tasks
bootJar.enabled = false

allprojects {
    group = "io.beaniejoy.dongecafe"
    version = Version.projectVersion

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin(Plugins.java)
        plugin(Plugins.Spring.dependencyManagement)
        plugin(Plugins.Spring.boot)

        plugin(Plugins.Kotlin.KOTLIN)
        plugin(Plugins.Kotlin.kotlinSpring)
        plugin(Plugins.Kotlin.kotlinJpa)
    }

    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencies {
        // Spring Boot Project
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // DB
        runtimeOnly("mysql:mysql-connector-java") // MySQL
        runtimeOnly("com.h2database:h2") // H2

        // JWT
        implementation("io.jsonwebtoken:jjwt-api:${Version.Deps.Jwt}")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:${Version.Deps.Jwt}")
        runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Version.Deps.Jwt}")

        // Logging
        implementation("io.github.microutils:kotlin-logging:${Version.Deps.KOTLIN_LOGGING}")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

    // gradle test logging
    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            events = setOf(
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT,
                TestLogEvent.STANDARD_ERROR
            )

            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }

//        ignoreFailures = true

        addTestListener(object : TestListener {
            override fun beforeSuite(suite: TestDescriptor?) {}
            override fun afterSuite(desc: TestDescriptor, result: TestResult) {
                if (desc.parent != null) return

                val summary = TestOutcome().apply {
                    add("${project.name}:${name} Test result: ${result.resultType}")
                    add("Test summary: ${result.testCount} tests, ${result.successfulTestCount} succeeded, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped")
                    add("report file: ${reports.html.entryPoint}")
                }

                if (result.resultType == TestResult.ResultType.SUCCESS) {
                    testResults.add(0, summary)
                } else {
                    testResults.add(summary)
                }
            }

            override fun beforeTest(testDescriptor: TestDescriptor?) {}
            override fun afterTest(testDescriptor: TestDescriptor?, result: TestResult?) {}
        })
    }
}

gradle.buildFinished {
    if (testResults.isNotEmpty()) {
        printResults(testResults)
    }
}

fun printResults(allResults:List<TestOutcome>) {
    val maxLength = allResults.maxOfOrNull { it.maxWidth() } ?: 0

    println("┌${"─".repeat(maxLength)}┐")

    println(allResults.joinToString("├${"─".repeat(maxLength)}┤\n") { testOutcome ->
        testOutcome.lines.joinToString("│\n│", "│", "│") {
            it + " ".repeat(maxLength - it.length)
        }
    })

    println("└${"─".repeat(maxLength)}┘")
}

val testResults by extra(mutableListOf<TestOutcome>()) // Container for tests summaries

data class TestOutcome(val lines: MutableList<String> = mutableListOf()) {
    fun add(line: String) {
        lines.add(line)
    }

    fun maxWidth(): Int {
        return lines.maxBy { it.length }?.length ?: 0
    }
}