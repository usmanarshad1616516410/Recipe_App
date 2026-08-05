# Fix: Unresolved reference 'enable' in build.gradle.kts

The project was failing to sync because of an incorrect DSL usage in the `app/build.gradle.kts` file. Specifically, the `optimization { enable = false }` block was using an invalid property.

## Changes Made

### Build Configuration

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/build.gradle.kts)

Replaced the invalid `optimization` block with the standard `isMinifyEnabled = false` property to correctly disable minification and optimizations for the release build.

```diff
     buildTypes {
         release {
-            optimization {
-                enable = false
-            }
+            isMinifyEnabled = false
+            proguardFiles(
+                getDefaultProguardFile("proguard-android-optimize.txt"),
+                "proguard-rules.pro"
+            )
         }
     }
```

## Verification Results

### Automated Tests
- **Gradle Sync**: Executed and finished successfully.
