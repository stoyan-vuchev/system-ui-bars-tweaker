# System UI Bars Tweaker [![](https://jitpack.io/v/stoyan-vuchev/system-ui-bars-tweaker.svg)](https://jitpack.io/#stoyan-vuchev/system-ui-bars-tweaker)

> A Jetpack Compose library for Android that continues the legacy of the Accompanist System UI Controller, with easy-to-use utilities to tweak the color, behavior, and visibility of the System UI bars.

## Table of Contents

* [Introduction](#introduction)
* [Usage](#usage)
* [Requirements](#requirements)
* [Gradle Kotlin DSL Setup](#gradle-kotlin-dsl-setup)
* [Gradle Groovy Setup](#gradle-groovy-setup)
* [Notice](#notice)
* [License](#license)
* [Contact](#contact)

## Introduction

* On August 24th, 2023, the [Accompanist System UI Controller](https://google.github.io/accompanist/systemuicontroller/) library was deprecated in favor of
the new [Activity.enableEdgeToEdge](https://developer.android.com/reference/androidx/activity/ComponentActivity#(androidx.activity.ComponentActivity).enableEdgeToEdge(androidx.activity.SystemBarStyle,androidx.activity.SystemBarStyle)) method available in androidx.activity ``1.8.0-alpha03`` and later.

* As of writing this documentation (September 14th, 2023), the new method only supports enabling Edge-To-Edge
functionallity and applying light/dark scrim to the System UI bars. Therefore, losing the easy-to-use utilities
for tweaking the color, behavior, and visibility. That's the main reason I've decided to create the **System UI Bars Tweaker** library.

* This library provides all of the [Accompanist System UI Controller](https://google.github.io/accompanist/systemuicontroller/) library + Edge-To-Edge functionality out of the box.

## Usage

### It's important to note a few things

* The library is written with efficiency and flexibility in mind, simply by following an approach based on a Producer and Consumer. Think of it as a parent providing support to their children.

* Edge-To-Edge, transparent status & navigation bars (without a scrim for API >= 26) are enabled by default.

* It's recomended to use a single top-level [SystemUIBarsTweaker](./system-ui-bars-tweaker/src/main/java/com/stoyanvuchev/systemuibarstweaker/SystemUIBarsTweaker.kt) instance per activity/dialog [Window](https://developer.android.com/reference/android/view/Window) and consume it down the composition.

* Here is an example by wrapping a Material3 app theme inside a [ProvideSystemUIBarsTweaker](./system-ui-bars-tweaker/src/main/java/com/stoyanvuchev/systemuibarstweaker/LocalSystemUIBarsTweaker.kt) composable (a Producer) and declaring a tweaker variable by using the [LocalSystemUIBarsTweaker](./system-ui-bars-tweaker/src/main/java/com/stoyanvuchev/systemuibarstweaker/LocalSystemUIBarsTweaker.kt) key (a Consumer). You can declare more than one variable anywhere down the composition, as long as there is a Producer to provide the top-level instance.

``` kotlin
@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    ...
) {

    ...

    ProvideSystemUIBarsTweaker {

        val tweaker = LocalSystemUIBarsTweaker.current

        DisposableEffect(tweaker, darkTheme) {

            // Update all of the system bar colors to be transparent,
            // use dark icons if we're in light theme,
            // and disable the scrim (enforced contrast).

            tweaker.tweakSystemBarsStyle(
                statusBarStyle = SystemBarStyle(
                    color = Color.Transparent,
                    darkIcons = !darkTheme,
                    enforceContrast = false
                ),
                navigationBarStyle = SystemBarStyle(
                    color = Color.Transparent,
                    darkIcons = !darkTheme,
                    enforceContrast = false
                )
            )

            // tweakStatusBarStyle() and tweakNavigationBarStyle() also exist.

            // tweakStatusBarVisibility(), tweakNavigationBarVisibility(), and tweakSystemBarsBehavior() as well.

             onDispose {}
        }

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )

    }

}
```

* If you don't want to use the Provider and Consumer approach, you can simply create an instance using a `rememberSystemUIBarsTweaker()` composable.

``` kotlin
val tweaker = rememberSystemUIBarsTweaker()
```

## Requirements

* Project minSdk version - `23`
* Project compileSdk version - `34`
* Jetpack Compose version - `1.5.1`
* Jetpack Compose Compiler version - `1.5.3`
* Kotlin version - `1.9.10`

## Gradle Kotlin DSL Setup

#### Step 1

* Add the Jitpack maven repository inside the `dependencyResolutionManagement` block of the  `settings.gradle` file if you don't have it already.

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io/") // this one
    }
}
```

#### Step 2

* Add the dependency in your module `build.gradle.kts` file.
* Latest version: [![](https://jitpack.io/v/stoyan-vuchev/system-ui-bars-tweaker.svg)](https://jitpack.io/#stoyan-vuchev/system-ui-bars-tweaker)

```kotlin
implementation("com.github.stoyan-vuchev:system-ui-bars-tweaker:<version>")
```

* Or if you're using a version catalog (e.g. `libs.versions.toml`), declare it there.

```toml
[versions]
systemUIBarsTweaker = "<version>"

[libraries]
systemUIBarsTweaker = { group = "com.github.stoyan-vuchev", name = "system-ui-bars-tweaker", version.ref = "systemUIBarsTweaker" }
```

* Then include the dependency in your module `build.gradle.kts` file.

```kotlin
implementation(libs.systemUIBarsTweaker)
```

#### Step 3

* ### Sync and rebuild the project. ✅

<br/>

## Gradle Groovy Setup

#### Step 1

* Add the Jitpack maven repository inside the `dependencyResolutionManagement` block of the  `settings.gradle` file if you don't have it already.

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // this one
    }
}
```

#### Step 2

* Add the dependency in your module `build.gradle` file.
* Latest version: [![](https://jitpack.io/v/stoyan-vuchev/system-ui-bars-tweaker.svg)](https://jitpack.io/#stoyan-vuchev/system-ui-bars-tweaker)

```groovy
implementation 'com.github.stoyan-vuchev:system-ui-bars-tweaker:<version>'
```

#### Step 3

* ### Sync and rebuild the project. ✅

<br/>

## Notice

This project includes software components that are subject to the Apache License, Version 2.0.

Portions of this software are copyright 2022 The Android Open Source Project.

The majority of the code in this project has been extensively modified and adapted
to suit the specific requirements and functionality of this project.

For more information, please refer to the [NOTICE](./NOTICE) file.

Attributions:

* The Android Open Source Project: <https://github.com/stoyan-vuchev/accompanist/blob/compose-1.5/systemuicontroller/src/main/java/com/google/accompanist/systemuicontroller/SystemUiController.kt>

## License

This project is open source and licensed under the [Apache License, Version 2.0](./LICENSE)

## Contact

Created by [@stoyan-vuchev](https://github.com/stoyan-vuchev/)
<br/>
E-mail - [contact.stoyan.vuchev@gmail.com](mailto://contact.stoyan.vuchev@gmail.com)
