dependencies {
    implementation(project(":domain"))
    testImplementation(testFixtures(project(":domain")))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Version.Deps.JWT}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Version.Deps.JWT}")
}