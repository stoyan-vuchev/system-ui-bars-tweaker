plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlinx.serialization)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
}

android {

    namespace = "com.stoyanvuchev.systemuibarstweaker.demo"
    compileSdk = 35

    defaultConfig {

        applicationId = "com.stoyanvuchev.systemuibarstweaker.demo"
        minSdk = 23
        targetSdk = 35
        versionCode = 3
        versionName = "1.2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures.compose = true

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    // Library
    implementation(project(":system-ui-bars-tweaker"))

    // Core
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.material)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)

    // Compose
    implementation(libs.activity.compose)
    implementation(libs.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material.icons.extended)
    implementation(libs.material3)
    implementation(libs.material3.windowsizeclass)

    // Rich Text
    implementation(libs.rich.text)
    implementation(libs.rich.text.m3)

    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)

    // Responsive Scaffold
    implementation(libs.stoyan.vuchev.responsive.scaffold)

    // Serialization
    implementation(libs.google.gson)

    // Android Testing
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.assertk)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.ui.test.manifest)
    androidTestImplementation(libs.material3.windowsizeclass)
    androidTestImplementation(libs.stoyan.vuchev.responsive.scaffold)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.assertk)

    // Debug
    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

}