# api-catalog API Specification

OpenAPI specification for the public catalog API of the Book Publishing platform.

## Overview

This module defines a read-only public API for consuming catalog information.

Main OpenAPI file: `spec/api-catalog/openapi.yaml`

## API Endpoints

- `GET /api/v1/books`: list books with pagination.

## Local Development

```bash
# Validate module OpenAPI specification
./gradlew :spec:api-catalog:openApiValidate

# Generate Kotlin Spring code
./gradlew :spec:api-catalog:openApiGenerate

# Build module
./gradlew :spec:api-catalog:build
```

Generated files are placed in `spec/api-catalog/build/generated-sources/openapi/`.

## Package Publication

```bash
# Publish with explicit version
./gradlew :spec:api-catalog:publish -PpackageVersion=0.1.0
```

Package coordinates:
- **Group**: `org.cescfe`
- **Artifact**: `book-publishing-api-catalog-api-spec`
- **Version**: from `-PpackageVersion` (default `0.1.0`)
- **Repository**: Google Artifact Registry

## Release Tag Format

Use workflow **Create and Push Spec Tag** with:
- `spec=api-catalog`
- `version=vX.Y.Z`

It will create a tag in this format:
- `spec/api-catalog/vX.Y.Z`
