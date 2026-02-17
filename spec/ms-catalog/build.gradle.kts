plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.spring") version "2.1.21"
    id("org.openapi.generator") version "7.15.0"
    `maven-publish`
    id("com.google.cloud.artifactregistry.gradle-plugin") version "2.2.0"
}

version = providers.gradleProperty("packageVersion").orElse("1.5.0").get()

apply(from = rootProject.file("gradle/spec-module.gradle"))
