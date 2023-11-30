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
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("org.springframework.data.redis.core.RedisHash") // redis hash
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("org.springframework.data.redis.core.RedisHash") // redis hash
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Version.Deps.JWT}")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${Version.Deps.QUERYDSL}:jakarta")
    kapt("com.querydsl:querydsl-apt:${Version.Deps.QUERYDSL}:jakarta")

    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}
