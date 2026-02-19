# Book Publishing API Specification

[![GitHub release](https://img.shields.io/github/v/release/CescFe/book-publishing-api-spec?color=blue)](https://github.com/CescFe/book-publishing-api-spec/releases/latest)
[![GitHub license](https://img.shields.io/github/license/CescFe/book-publishing-api-spec?color=blue)](https://github.com/CescFe/book-publishing-api-spec/blob/main/LICENSE)

Modular API specification repository for a Book Publishing system with automatic Kotlin Spring code generation.

## Overview

This repository contains OpenAPI 3.1.2 specifications organized by module under `spec/<spec-name>`. Each module is an independent Gradle project with its own OpenAPI validation, code generation, and package publication lifecycle.

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
  api-catalog/
    openapi.yaml
    build.gradle.kts
    ...
  ms-catalog/
    openapi.yaml
    build.gradle.kts
    ...
```

Each folder under `spec/` is an independent Gradle module with its own OpenAPI generation and publication lifecycle.

## Spec Modules

- [`api-catalog`](spec/api-catalog/README.md): Public read-only API for catalog consumption
- [`ms-catalog`](spec/ms-catalog/README.md): Internal API for catalog management (authors, books, and collections)

Module-specific details (endpoints, schemas, package coordinates) live in each module README.

## Local Development

### Prerequisites

- Java 21 or higher

### Validation

```bash
# List available projects/modules
./gradlew projects

# Validate one spec module
./gradlew :spec:<spec-name>:openApiValidate

# Generate Kotlin Spring code for one module
./gradlew :spec:<spec-name>:openApiGenerate

# Build all modules
./gradlew build
```

## CI/CD Workflow

### Automatic Validation

Every pull request automatically runs (for each module found in `spec/*`):
- ✅ **OpenAPI Specification Validation** - Validates the YAML specification
- ✅ **Build and Compilation** - Generates code and verifies compilation

### Release Process

The release process is partially automated and consists of these steps:

#### 1. Create Tag

1. Go to **Actions** → **Create and Push Spec Tag**
2. Run manually with:
   - `spec` (for example, `api-catalog` or `ms-catalog`)
   - `version` (for example, `v1.4.0`)
3. This creates a module-scoped tag with format `spec/<spec>/vX.Y.Z` (for example, `spec/ms-catalog/v1.4.0`)

#### 2. Release Tag

1. Go to **Tags**
2. Click on the tag created in the previous step → Click on **Release**
3. Autogenerate release notes

#### 3. Publish Package

1. Go to **Actions** → **Publish Package**
2. Run manually with the module and version (e.g., `api-catalog`, `0.1.0`)
3. The selected module package is published to Google Artifact Registry

##### Manual Publishing
```bash
./gradlew :spec:<spec-name>:publish -PpackageVersion=0.1.0
```

## Consuming the Generated Code

Package coordinates are module-specific and follow this pattern:
- **Group**: `org.cescfe`
- **Artifact**: `book-publishing-<spec-name>-spec`
- **Version**: your published version
- **Repository**: Google Artifact Registry

See each module README for concrete dependency examples.
