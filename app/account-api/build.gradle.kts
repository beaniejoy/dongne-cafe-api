afterEvaluate {
    project.tasks.withType<Test> {
        dependsOn("${SubModule.APP_COMMON}:test")
    }
}

dependencies {
    implementation(project(SubModule.APP_COMMON))
    implementation(project(SubModule.DOMAIN))
    testImplementation(testFixtures(project(SubModule.DOMAIN)))
    runtimeOnly(project(SubModule.DB))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Version.Deps.JWT}")

    testImplementation("org.springframework.security:spring-security-test")
}