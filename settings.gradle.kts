rootProject.name = "product-ddd"

pluginManagement {
    val kotlinVersion = "1.9.22"
    val springBootVersion = "3.2.0"
    val springDependencyManagementVersion = "1.1.4"

    val kLintVersion = "12.0.2"

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion

        kotlin("kapt") version kotlinVersion

        // Kotlin Formatting
        id("org.jlleitschuh.gradle.ktlint") version kLintVersion
    }
}

fun File.registerSubDirectoriesAsModule() {
    this.listFiles()?.forEach { file ->
        if (file.isDirectory) {
            include("${this.name}:${file.name}")
        }
    }
}

// 모듈 등록
listOf("commons", "core", "application")
    .map { File(it) }
    .map { it.registerSubDirectoriesAsModule() }
