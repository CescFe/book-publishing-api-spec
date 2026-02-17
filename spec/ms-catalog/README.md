# ms-catalog API Specification

OpenAPI specification for the catalog domain of the Book Publishing platform.

## Overview

This module defines the API for:
- authentication
- authors
- books
- collections

Main OpenAPI file: `spec/ms-catalog/openapi.yaml`

## API Endpoints

### Authentication
- `POST /api/v1/auth/login` - Authenticate user and get access token

### Authors
- `GET /api/v1/authors` - List authors (paginated)
- `POST /api/v1/authors` - Create a new author
- `GET /api/v1/authors/all` - List all authors (non-paginated)
- `GET /api/v1/authors/{id}` - Get author by ID
- `PUT /api/v1/authors/{id}` - Update author
- `DELETE /api/v1/authors/{id}` - Delete author

### Books
- `GET /api/v1/books` - List books (paginated)
- `POST /api/v1/books` - Create a new book
- `GET /api/v1/books/all` - List all books (non-paginated)
- `GET /api/v1/books/{id}` - Get book by ID
- `PUT /api/v1/books/{id}` - Update book
- `DELETE /api/v1/books/{id}` - Delete book

### Collections
- `GET /api/v1/collections` - List collections (paginated)
- `POST /api/v1/collections` - Create a new collection
- `GET /api/v1/collections/all` - List all collections (non-paginated)
- `GET /api/v1/collections/{id}` - Get collection by ID
- `PUT /api/v1/collections/{id}` - Update collection
- `DELETE /api/v1/collections/{id}` - Delete collection

## Local Development

```bash
# Validate module OpenAPI specification
./gradlew :spec:ms-catalog:openApiValidate

# Generate Kotlin Spring code
./gradlew :spec:ms-catalog:openApiGenerate

# Build module
./gradlew :spec:ms-catalog:build
```

Generated files are placed in `spec/ms-catalog/build/generated-sources/openapi/`.

## Package Publication

```bash
# Publish with explicit version
./gradlew :spec:ms-catalog:publish -PpackageVersion=1.4.0
```

Package coordinates:
- **Group**: `org.cescfe`
- **Artifact**: `book-publishing-ms-catalog-api-spec`
- **Version**: from `-PpackageVersion` (default `1.4.0`)
- **Repository**: Google Artifact Registry

## Release Tag Format

Use workflow **Create and Push Spec Tag** with:
- `spec=ms-catalog`
- `version=vX.Y.Z`

It will create a tag in this format:
- `spec/ms-catalog/vX.Y.Z`
