# Book Publishing API Specification

[![GitHub release](https://img.shields.io/github/v/release/CescFe/book-publishing-api-spec?color=blue)](https://github.com/CescFe/book-publishing-api-spec/releases/latest)
[![GitHub license](https://img.shields.io/github/license/CescFe/book-publishing-api-spec?color=blue)](https://github.com/CescFe/book-publishing-api-spec/blob/main/LICENSE)

Modular API specification repository for a Book Publishing system with automatic Kotlin Spring code generation.

## Overview

This repository contains OpenAPI 3.1.2 specifications organized by module under `spec/<spec-name>`. The current module (`spec/ms-catalog`) manages books, authors, and collections, and is automatically validated to generate Kotlin Spring Boot 3 compatible code.

## Features

- ✅ **OpenAPI 3.1.2** specification with comprehensive CRUD operations
- ✅ **Modular structure** for multiple independent specs (`spec/<spec-name>`)
- ✅ **Authentication & Authorization** with Bearer token support
- ✅ **Automatic validation** on every pull request
- ✅ **Kotlin Spring code generation** with Jackson serialization
- ✅ **Artifact Registry publishing** for dependency consumption
- ✅ **Automated release workflow** with tag creation and package publishing
- ✅ **Audit fields** (created_at, created_by, updated_at, updated_by)

## Repository Structure

```text
spec/
  ms-catalog/
    openapi.yaml
    build.gradle.kts
    ...
```

Each folder under `spec/` is an independent Gradle module with its own OpenAPI generation and publication lifecycle.

## API Endpoints

### Authentication
- `POST /api/v1/auth/login` - Authenticate user and get access token

### Authors
- `GET /api/v1/authors` - List of authors (paginated)
- `POST /api/v1/authors` - Create a new author
- `GET /api/v1/authors/all` - List all authors (non-paginated)
- `GET /api/v1/authors/{id}` - Get author by ID
- `PUT /api/v1/authors/{id}` - Update author
- `DELETE /api/v1/authors/{id}` - Delete author

### Books
- `GET /api/v1/books` - List of books (paginated)
- `POST /api/v1/books` - Create a new book
- `GET /api/v1/books/all` - List all books (non-paginated)
- `GET /api/v1/books/{id}` - Get book by ID
- `PUT /api/v1/books/{id}` - Update book
- `DELETE /api/v1/books/{id}` - Delete book

### Collections
- `GET /api/v1/collections` - List of collections (paginated)
- `POST /api/v1/collections` - Create a new collection
- `GET /api/v1/collections/all` - List all collections (non-paginated)
- `GET /api/v1/collections/{id}` - Get collection by ID
- `PUT /api/v1/collections/{id}` - Update collection
- `DELETE /api/v1/collections/{id}` - Delete collection

## Local Development

### Prerequisites

- Java 21 or higher

### Validation

```bash
# Validate ms-catalog OpenAPI specification
./gradlew :spec:ms-catalog:openApiValidate

# Generate Kotlin Spring code for ms-catalog
./gradlew :spec:ms-catalog:openApiGenerate

# Build all modules
./gradlew build
```

### Generated Code

The generated code includes:
- **DTOs**: Kotlin data classes for all schemas
- **API Interfaces**: Spring Boot 3 compatible interfaces for all endpoints
- **Jackson Serialization**: Configured for JSON serialization/deserialization
- **Bean Validation**: Jakarta validation annotations
- **Security**: Bearer token authentication support

Generated files for `ms-catalog` are placed in `spec/ms-catalog/build/generated-sources/openapi/`.

## CI/CD Workflow

### Automatic Validation

Every pull request automatically runs (for each module found in `spec/*`):
- ✅ **OpenAPI Specification Validation** - Validates the YAML specification
- ✅ **Build and Compilation** - Generates code and verifies compilation

### Release Process

The release process is partially automated and consists of these steps:

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
2. Run manually with the module and version (e.g., `ms-catalog`, `0.1.0`)
3. The selected module package is published to Google Artifact Registry

##### Manual Publishing
```bash
./gradlew :spec:ms-catalog:publish -PpackageVersion=0.1.0
```

## Consuming the Generated Code

### In your Spring Boot project

Add to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
    maven {
        name = "ArtifactRegistry"
        url = uri("artifactregistry://my_uri")
    }
}

dependencies {
    implementation("org.cescfe:book-publishing-ms-catalog-api-spec:0.1.0") // or the desired version
}
```

### Package Coordinates

- **Group**: `org.cescfe`
- **Artifact**: `book-publishing-ms-catalog-api-spec`
- **Version**: `0.1.0` (or latest)
- **Repository**: Google Artifact Registry
