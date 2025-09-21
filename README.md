# book-publishing-api-spec
API documentation for a Book Publishing

## Validation

The OpenAPI specification is automatically validated on every pull request using GitHub Actions.

### Local Validation

```bash
npm install -g @redocly/cli

# Validate specification
redocly lint specs/openapi.yaml

# Generate documentation
redocly build-docs specs/openapi.yaml --output=docs/index.html
```
