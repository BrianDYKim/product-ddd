dependencies {
    // common modules
    implementation(project(":commons:common-architect"))

    // domain module
    implementation(project(":core:domain"))

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
}
