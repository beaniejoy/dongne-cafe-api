import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    kotlin(Plugins.Kotlin.PLUGIN_JPA).version(Version.KOTLIN)
    `java-test-fixtures`
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Version.Deps.JWT}")
}
