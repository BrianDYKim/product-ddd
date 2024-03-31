dependencies {
    // common modules
    implementation(project(":commons:hibernate-protect"))
    implementation(project(":commons:common-architect"))
    implementation(project(":commons:common-util"))

    // domain modules
    implementation(project(":core:domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
}
