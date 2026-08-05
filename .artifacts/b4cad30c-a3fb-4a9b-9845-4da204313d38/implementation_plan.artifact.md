# Fix Kotlin Version Incompatibility and Build Errors

The project is currently experiencing a Kotlin version mismatch where the compiler (v2.2.0) is attempting to read the Kotlin standard library (v2.4.0). This leads to metadata incompatibility and failures to resolve basic standard library functions like `listOf`. Additionally, there are missing plugin applications and parameter mismatch in the `Recipe` data class.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/gradle/libs.versions.toml)
- Update Kotlin version to `2.4.10` to match the metadata version found in the stdlib.
- Add `kotlin-android` plugin definition.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/build.gradle.kts) (Root)
- Add `kotlin-android` plugin to the top-level plugins block.

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/build.gradle.kts)
- Apply the `kotlin-android` plugin.

### Data Model Fixes

#### [MODIFY] [Recipe.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/Recipe.kt)
- Provide default values for `ingredients` and `instructions` to fix parameter mismatch errors in existing usages (like in `HomeState` and `HomeScreen`).

## Verification Plan

### Automated Tests
- Run `gradlew :app:assembleDebug` to ensure the project builds successfully.
- Run Compose Preview for `HomeScreen` to verify rendering.
