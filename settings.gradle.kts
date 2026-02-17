rootProject.name = "book-publishing-api-spec"

val specRoot = file("spec")

if (specRoot.exists()) {
    specRoot
        .listFiles()
        ?.filter { it.isDirectory && file("spec/${it.name}/build.gradle.kts").exists() }
        ?.sortedBy { it.name }
        ?.forEach { include("spec:${it.name}") }
}
