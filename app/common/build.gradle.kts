import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

afterEvaluate {
    project.tasks.apply {
        this.withType<Test> {
            dependsOn("${SubModule.DOMAIN}:test")
        }

        this.withType<KtLintCheckTask> {
            dependsOn(
                ":ktlintCheck",
                "${SubModule.DB}:ktlintCheck",
                "${SubModule.DOMAIN}:ktlintCheck",
                "${SubModule.INFRA}:ktlintCheck"
            )
        }
    }
}

dependencies {
    implementation(project(SubModule.DOMAIN))
    testImplementation(testFixtures(project(SubModule.DOMAIN)))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("com.google.code.gson:gson")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Version.Deps.JWT}")

    testImplementation("org.springframework.security:spring-security-test")
}
