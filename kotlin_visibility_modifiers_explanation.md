# Kotlin Visibility Modifiers: Internal vs Private

## Overview
Kotlin provides several visibility modifiers to control access to classes, functions, properties, and other declarations. This document explains the key differences between `internal` and `private` modifiers.

## Visibility Modifiers in Kotlin

### 1. `private` - Most Restrictive
**Scope**: Visible only within the **same file** (for top-level declarations) or **same class/object** (for class members)

**Key Characteristics**:
- Most restrictive visibility
- Cannot be accessed from outside the declaring scope
- For top-level declarations: visible only within the same file
- For class members: visible only within the same class

### 2. `internal` - Module-Level Visibility
**Scope**: Visible within the **same module**

**Key Characteristics**:
- Module-level visibility
- Can be accessed from anywhere within the same module
- Cannot be accessed from other modules
- A "module" in Kotlin is a set of Kotlin files compiled together (e.g., a Gradle module)

## Examples from Current Codebase

### Private Examples

```kotlin
// From BookSearchScreen.kt
@Composable
private fun BookSearchBar(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit
) {
    // This function is only visible within BookSearchScreen.kt file
    // Cannot be called from other files, even within the same module
}

@Composable
private fun BooksList(viewState: BookSearchViewState) {
    // Only accessible within BookSearchScreen.kt
}

@Composable
private fun BookSearchError(error: String, onClearError: () -> Unit) {
    // Only accessible within BookSearchScreen.kt
}
```

### Internal Examples

```kotlin
// From BookSearchScreen.kt
@Composable
internal fun BookSearchScreenInternal(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit,
    onClearError: () -> Unit
) {
    // This function is visible throughout the openlibraryBooks module
    // Can be called from any file within the same module
    // Cannot be called from the main app module
}

@Composable
internal fun BookItem(book: Book) {
    // Visible throughout the openlibraryBooks module
    // Hidden from external modules like the main app
}
```

## Practical Comparison

| Aspect | `private` | `internal` |
|--------|-----------|------------|
| **Scope** | Same file/class only | Same module |
| **Cross-file access** | ❌ No | ✅ Yes (within module) |
| **Cross-module access** | ❌ No | ❌ No |
| **Use case** | Implementation details, helper functions | Module API, shared utilities |
| **Refactoring impact** | Minimal (file-scoped) | Module-scoped |

## When to Use Each

### Use `private` when:
- ✅ Function/property is only needed within the same file
- ✅ Implementation detail that shouldn't be exposed
- ✅ Helper functions for a specific composable/class
- ✅ You want maximum encapsulation

**Example from codebase**:
```kotlin
// BookSearchBar is only used by BookSearchScreenInternal in the same file
@Composable
private fun BookSearchBar(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit
) {
    // Implementation details...
}
```

### Use `internal` when:
- ✅ Function/property needs to be shared across files within the module
- ✅ Part of the module's internal API (not for external consumption)
- ✅ Testing utilities that need module-wide access
- ✅ Shared components within a feature module

**Example from codebase**:
```kotlin
// BookSearchScreenInternal might be used by other files in the module
// but shouldn't be exposed to external modules
@Composable
internal fun BookSearchScreenInternal(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit,
    onClearError: () -> Unit
) {
    // Implementation details...
}
```

## Module Structure in Our Project

```
📦 Main App Module (app)
├── ComposeFragment.kt (can access public APIs from openlibraryBooks)
└── ...

📦 OpenLibrary Module (openlibraryBooks)
├── BookSearchScreen.kt
│   ├── BookSearchScreen() - public (accessible from main app)
│   ├── BookSearchScreenInternal() - internal (module-only)
│   ├── BookItem() - internal (module-only)
│   ├── BookSearchBar() - private (file-only)
│   ├── BooksList() - private (file-only)
│   └── BookSearchError() - private (file-only)
└── ...
```

## Access Matrix

| From → To | `private` in same file | `private` in other file | `internal` in same module | `internal` in other module |
|-----------|------------------------|-------------------------|---------------------------|----------------------------|
| **Same file** | ✅ Yes | ❌ No | ✅ Yes | ❌ No |
| **Other file, same module** | ❌ No | ❌ No | ✅ Yes | ❌ No |
| **Other module** | ❌ No | ❌ No | ❌ No | ❌ No |

## Best Practices

### 1. Start with Most Restrictive
- Begin with `private`
- Expand to `internal` only when needed across files
- Make `public` only when needed by external modules

### 2. Clear API Boundaries
```kotlin
// Public API - external modules can use
@Composable
fun BookSearchScreen() {
    // External entry point
}

// Internal API - module implementation
@Composable
internal fun BookSearchScreenInternal(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit,
    onClearError: () -> Unit
) {
    // Internal implementation
}

// Private helpers - file-scoped utilities
@Composable
private fun BookSearchBar(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit
) {
    // Private helper
}
```

### 3. Module Design
- Use `internal` for module's internal architecture
- Use `private` for implementation details
- Keep public API minimal and stable

## Summary

- **`private`**: Most restrictive, file/class-scoped, for implementation details
- **`internal`**: Module-scoped, for internal module APIs, hidden from external modules
- **`public`** (default): Accessible everywhere, use sparingly for stable APIs

The choice between `internal` and `private` depends on whether you need cross-file access within your module. Use `private` for maximum encapsulation, `internal` for module-wide sharing.
