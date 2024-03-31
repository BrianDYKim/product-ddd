import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val mockkVersion = "1.10.3"
val kotestVersion = "5.3.2"

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("org.jetbrains.kotlin.plugin.noarg")
    id("org.jetbrains.kotlin.plugin.jpa")
    kotlin("jvm")
    kotlin("plugin.spring")

    kotlin("kapt")

    // KLint
    id("org.jlleitschuh.gradle.ktlint")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    group = "team.me"
    version = "1.0.0"

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")

    apply(plugin = "kotlin-kapt")

    // KLint
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    dependencies {
        // Kotlin Standard Library
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        // Jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

        // Spring
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework:spring-tx")
        kapt("org.springframework.boot:spring-boot-configuration-processor")

        // Validation
        implementation("jakarta.validation:jakarta.validation-api")
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    // apply ktlint for debug
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

val nonDependencyProjects =
    File("commons").listFiles()!!.filter { it.isDirectory }
        .map { it.name }

configure(subprojects.filter { it.name !in nonDependencyProjects }) {
    dependencies {
        // Spring
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        // Test
        testImplementation("io.mockk:mockk:$mockkVersion")
        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion") // for kotest framework
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion") // for kotest core jvm assertions
        testImplementation("io.kotest:kotest-property:$kotestVersion") // for kotest property test
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

// Jar
tasks {
    named<Jar>("jar") {
        enabled = true
    }

    named<BootJar>("bootJar") {
        enabled = false
    }
}
