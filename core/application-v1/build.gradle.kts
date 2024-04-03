dependencies {
    // common modules
    implementation(project(":commons:common-architect"))

    // domain module
    implementation(project(":core:domain"))

    // external-jpa module
    implementation(project(":core:external-jpa"))

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
}
