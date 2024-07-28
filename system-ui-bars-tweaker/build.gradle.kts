plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
    `maven-publish`
}

android {

    namespace = "com.stoyanvuchev.systemuibarstweaker"
    compileSdk = 35

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {

                groupId = "com.github.stoyan-vuchev"
                artifactId = "system-ui-bars-tweaker"
                version = "1.0.2"

                afterEvaluate {
                    from(components["release"])
                }

            }
        }
    }
}

dependencies {

    // Core

    implementation(libs.core.ktx)
    implementation(libs.activity.compose)

    // Compose

    implementation(platform(libs.compose.bom))
    implementation(libs.runtime)
    implementation(libs.foundation)

    // Testing

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.assertk)
    androidTestImplementation(libs.espresso.core)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.ui.test.manifest)

    testImplementation(libs.junit)

}