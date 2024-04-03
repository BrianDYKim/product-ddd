dependencies {
    // common modules
    implementation(project(":commons:common-architect"))

    // domain module
    implementation(project(":core:domain"))

    // external-jpa module
    implementation(project(":core:external-jpa"))

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
}
