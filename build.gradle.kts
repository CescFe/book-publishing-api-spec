import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.21"
    id("org.openapi.generator") version "7.15.0"
    id("com.diffplug.spotless") version "7.0.3"
}

group = "org.cescfe"
version = "0.1.0"

val ktLint = "1.5.0"

repositories {
    mavenCentral()
}

openApiValidate {
    inputSpec.set("$rootDir/specs/openapi.yaml")
    recommend = true
}

val generatedDir: String =
    layout.buildDirectory
        .dir("generated-sources/openapi")
        .get()
        .asFile.absolutePath
val openApiPackage = "org.cescfe.bookpublishing.infrastructure.openapi"

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/specs/openapi.yaml")
    outputDir.set(generatedDir)

    apiPackage.set("$openApiPackage.http.inbound")
    invokerPackage.set("$openApiPackage.invoker")
    modelPackage.set("$openApiPackage.http.inbound.model")
    packageName.set("$openApiPackage.http.inbound")
    configOptions.set(
        mapOf(
            "useTags" to "true",
            "useBeanValidation" to "true",
            "delegatePattern" to "false",
            "dateLibrary" to "java8",
            "useSpringBoot3" to "true",
            "enumPropertyNaming" to "UPPERCASE",
            "interfaceOnly" to "true",
            "modelPropertyNaming" to "original",
            "serializationLibrary" to "jackson",
            "skipDefaultInterface" to "true",
        ),
    )
}

sourceSets {
    main {
        kotlin {
            srcDirs(
                "$generatedDir/src/main/kotlin",
            )
        }
    }
}

tasks.named("openApiGenerate") {
    dependsOn("openApiValidate")

    doFirst {
        delete(generatedDir)
    }
}

tasks.named("compileKotlin").configure {
    dependsOn("openApiGenerate")
}

tasks.withType<KotlinCompile> {
    dependsOn("openApiGenerate")
}

listOf(tasks.compileJava, tasks.compileKotlin, tasks.compileTestJava, tasks.compileTestKotlin).forEach {
    it.get().mustRunAfter(tasks.spotlessCheck)
}

spotless {
    kotlin {
        ktlint(ktLint).setEditorConfigPath("$rootDir/.editorconfig")
        targetExclude("**/build/generated-sources/**/*.kt")
    }
    kotlinGradle {
        ktlint(ktLint).setEditorConfigPath("$rootDir/.editorconfig")
        target("*.gradle.kts")
    }
}
