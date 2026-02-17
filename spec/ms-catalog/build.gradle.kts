plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.openapi.generator)
    `maven-publish`
    alias(libs.plugins.artifactregistry)
}

version = providers.gradleProperty("packageVersion").orElse("1.5.1").get()

apply(from = rootProject.file("gradle/spec-module.gradle"))
