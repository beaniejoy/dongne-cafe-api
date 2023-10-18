import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    kotlin(Plugins.Kotlin.PLUGIN_JPA).version(Version.KOTLIN)
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
    implementation(project(SubModule.DOMAIN))
    testImplementation(testFixtures(project(SubModule.DOMAIN)))

    implementation("org.springframework.boot:spring-boot-starter-security")

    // DB
    runtimeOnly("mysql:mysql-connector-java:${Version.Deps.MYSQL}") // MySQL
    runtimeOnly("com.h2database:h2") // H2
}
