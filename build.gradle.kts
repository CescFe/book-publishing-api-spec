plugins {
    id("org.openapi.generator") version "7.15.0"
}

group = "org.cescfe"
version = "0.1.0"

repositories {
    mavenCentral()
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/specs/openapi.yaml")
    outputDir.set("${layout.buildDirectory.get()}/generated")
    apiPackage.set("com.bookpublishing.api")
    modelPackage.set("com.bookpublishing.model")
    invokerPackage.set("com.bookpublishing.invoker")
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "modelPropertyNaming" to "original",
            "serializationLibrary" to "jackson"
        )
    )
}

tasks.register("generateApi") {
    dependsOn("openApiGenerate")
    description = "Generate Kotlin Spring API code from OpenAPI specification"
    group = "code generation"
}