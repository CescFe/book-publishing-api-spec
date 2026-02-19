plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.openapi.generator)
    `maven-publish`
    alias(libs.plugins.artifactregistry)
}

version = providers.gradleProperty("packageVersion").orElse("0.1.2").get()

apply(from = rootProject.file("gradle/spec-module.gradle"))
