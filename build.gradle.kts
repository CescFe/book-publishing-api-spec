import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.spring") version "2.1.21"
    id("org.openapi.generator") version "7.15.0"
    `maven-publish`
}

group = "org.cescfe"
version = "0.1.0"

val developer = "FrancescFe"
val repositoryUrl = "https://github.com/FrancescFe/book-publishing-api-spec"
val packageUrl = "https://maven.pkg.github.com/FrancescFe/book-publishing-api-spec"

val generatedDir: String =
    layout.buildDirectory
        .dir("generated-sources/openapi")
        .get()
        .asFile.absolutePath
val openApiPackage = "org.cescfe.bookpublishing.infrastructure.openapi"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.20.0")
    compileOnly("jakarta.validation:jakarta.validation-api:3.1.1")
    compileOnly("io.swagger.core.v3:swagger-annotations:2.2.37")
    compileOnly("org.springframework.boot:spring-boot-starter-web:3.5.6")
    compileOnly("org.springframework.boot:spring-boot-starter-validation:3.5.6")
    compileOnly("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
    compileOnly("io.swagger.core.v3:swagger-models:2.2.37")
    compileOnly("io.swagger.core.v3:swagger-core:2.2.37")
}

openApiValidate {
    inputSpec.set("$rootDir/specs/openapi.yaml")
    recommend = true
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/specs/openapi.yaml")
    outputDir.set(generatedDir)

    apiPackage.set("$openApiPackage.http.inbound")
    invokerPackage.set("$openApiPackage.invoker")
    modelPackage.set("$openApiPackage.http.inbound.model")
    packageName.set("$openApiPackage.http.inbound")
    modelNameSuffix.set("DTO")
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "book-publishing-api-spec"
            version = project.version.toString()

            pom {
                name.set("Book Publishing API Specification")
                description.set("Generated Kotlin Spring code from OpenAPI specification")
                url.set(repositoryUrl)

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set(developer)
                        name.set(developer)
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri(packageUrl)
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
}
