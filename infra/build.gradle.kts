import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(SubModule.DOMAIN))
    testImplementation(testFixtures(project(SubModule.DOMAIN)))

    implementation("org.springframework.cloud:spring-cloud-starter-vault-config:3.1.3")

    // Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}
