# Implementation Plan - Fix Unresolved reference 'Meal' and Type Inconsistencies

The project has multiple issues:
1. `Meal.kt` lacks a package declaration, leading to import issues.
2. `MealRepositoryImpl.kt` is missing the `Meal` import.
3. `RecipeApi`, `MealRepository`, `Routes`, and `DetailViewModel` incorrectly use `RecipeApi` as a type for recipe IDs.
4. `getMeals()` in `MealRepository` and its implementation have incorrect return types (`Meal?` instead of `List<Meal>?`).

## Proposed Changes

### [Domain Layer]

#### [MODIFY] [Meal.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/domain/model/Meal.kt)
- Add `package com.example.recipeapp.domain.model` at the top.

#### [MODIFY] [MealRepository.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/domain/repsitory/MealRepository.kt)
- Update `getMeals()` return type to `List<Meal>?`.
- Update `getMealById(id: RecipeApi)` to `getMealById(id: String)`.

### [Data Layer]

#### [MODIFY] [RecipeApi.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/data/remote/RecipeApi.kt)
- Update `getRecipeById(@Path("id") id: RecipeApi)` to use `String` (or `Int` if preferred, but `Meal.id` is `String`). I'll use `String` to match `Meal`.

#### [MODIFY] [MealRepositoryImpl.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/data/repository/MealRepositoryImpl.kt)
- Add missing import for `com.example.recipeapp.domain.model.Meal`.
- Update `getMeals()` return type to `List<Meal>?`.
- Update `getMealById(id: RecipeApi)` to `getMealById(id: String)`.
- Update implementation of `getMealById` to call `api.getRecipeById(id)`.

#### [MODIFY] [Mapper.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/data/mapper/Mapper.kt)
- Correct `import Meal` to `import com.example.recipeapp.domain.model.Meal`.

### [Navigation & Presentation]

#### [MODIFY] [Routes.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/navigation/Routes.kt)
- Change `Routes.Detail(val recipe: RecipeApi)` to `Routes.Detail(val recipeId: String)`.

#### [MODIFY] [NavHost.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/navigation/NavHost.kt)
- Update `composable<Routes.Detail>` to pass `route.recipeId` to `DetailScreen`.

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/presentation/home/HomeScreen.kt)
- Update navigation calls to `Routes.Detail(recipeId = item.id)`.

#### [MODIFY] [DetailScreen.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/presentation/details/DetailScreen.kt)
- Update `recipeId` parameter type from `RecipeApi` to `String`.

#### [MODIFY] [DetailViewModel.kt](file:///C:/Users/Super/AndroidStudioProjects/RecipeApp/app/src/main/java/com/example/recipeapp/presentation/details/DetailViewModel.kt)
- Update `loadRecipe(id: RecipeApi)` to `loadRecipe(id: String)`.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to ensure all unresolved references and type mismatches are resolved.

### Manual Verification
- N/A (Build fix)
