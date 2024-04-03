dependencies {
    // Common modules
    implementation(project(":commons:hibernate-protect"))
    implementation(project(":commons:common-architect"))
    implementation(project(":commons:common-util"))

    // application module
    implementation(project(":application:common-web"))

    // core application module
    implementation(project(":core:application-v1"))

    // domain module
    implementation(project(":core:domain"))

    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
}
