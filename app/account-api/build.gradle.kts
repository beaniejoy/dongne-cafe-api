import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask

afterEvaluate {
    project.tasks.apply {
        this.withType<Test> {
            dependsOn("${SubModule.APP_COMMON}:test")
        }

        this.withType<KtLintCheckTask> {
            dependsOn("${SubModule.APP_COMMON}:ktlintCheck")
        }
    }
}

dependencies {
    implementation(project(SubModule.APP_COMMON))
    implementation(project(SubModule.DOMAIN))
    testImplementation(testFixtures(project(SubModule.DOMAIN)))
    implementation(project(SubModule.DB))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Version.Deps.JWT}")

    testImplementation("org.springframework.security:spring-security-test")
}
