# Book Publishing API Specification

API specification for a Book Publishing system with automatic Kotlin Spring code generation.

## Overview

This repository contains the OpenAPI 3.1.2 specification for a Book Publishing API that manages books, authors, and collections. The specification is automatically validated and used to generate Kotlin Spring Boot 3 compatible code.

## Features

- ✅ **OpenAPI 3.1.2** specification with comprehensive CRUD operations
- ✅ **Automatic validation** on every pull request
- ✅ **Kotlin Spring code generation** with Jackson serialization
- ✅ **GitHub Packages publishing** for dependency consumption
- ✅ **Automated release workflow** with tag creation and package publishing

## API Endpoints

### Authors
- `GET /api/v1/authors` - Get all authors (paginated)
- `POST /api/v1/authors` - Create a new author
- `GET /api/v1/authors/{id}` - Get author by ID
- `PUT /api/v1/authors/{id}` - Update author
- `DELETE /api/v1/authors/{id}` - Delete author

### Books
- `GET /api/v1/books` - Get all books (paginated)
- `POST /api/v1/books` - Create a new book
- `GET /api/v1/books/{id}` - Get book by ID
- `PUT /api/v1/books/{id}` - Update book
- `DELETE /api/v1/books/{id}` - Delete book

### Collections
- `GET /api/v1/collections` - Get all collections (paginated)
- `POST /api/v1/collections` - Create a new collection
- `GET /api/v1/collections/{id}` - Get collection by ID
- `PUT /api/v1/collections/{id}` - Update collection
- `DELETE /api/v1/collections/{id}` - Delete collection

## Local Development

### Prerequisites

- Java 21 or higher
- Gradle 8.5 or higher

### Validation

```bash
# Validate OpenAPI specification
./gradlew openApiValidate

# Generate Kotlin Spring code
./gradlew openApiGenerate

# Build the project
./gradlew build
```

### Generated Code

The generated code includes:
- **DTOs**: Kotlin data classes for all schemas
- **API Interfaces**: Spring Boot 3 compatible interfaces with `@RequestMapping` annotations
- **Jackson Serialization**: Configured for JSON serialization/deserialization
- **Bean Validation**: Jakarta validation annotations

Generated files are placed in `build/generated-sources/openapi/`.

## CI/CD Workflow

### Automatic Validation

Every pull request automatically runs:
- ✅ **OpenAPI Specification Validation** - Validates the YAML specification
- ✅ **Build and Compilation** - Generates code and verifies compilation

### Release Process

The release process is manual and consists of two steps:

#### 1. Create Tag

1. Go to **Actions** → **Create Release Tag**
2. Run manually with the desired version (e.g., `v0.1.0`)
3. This creates a Git tag and GitHub release

#### 2. Release Tag

1. Go to **Tags**
2. Click on the tag created in the previous step → Click on **Release**
3. Autogenerate release notes

#### 3. Publish Package

1. Go to **Actions** → **Publish Package**
2. Run manually with the version (e.g., `0.1.0`)
3. This publishes the JAR to GitHub Packages

## Consuming the Generated Code

### In your Spring Boot project

Add to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/CescFe/book-publishing-api-spec")
        credentials {
            username = System.getenv("USERNAME")
            password = System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation("org.cescfe:book-publishing-api-spec:0.1.0")
}
```

### Package Coordinates

- **Group**: `org.cescfe`
- **Artifact**: `book-publishing-api-spec`
- **Version**: `0.1.0` (or latest)
- **Repository**: `https://maven.pkg.github.com/CescFe/book-publishing-api-spec`
