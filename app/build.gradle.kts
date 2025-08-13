import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.baselineprofile)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.github.xnovo3000.gigapass"
    compileSdk = 36
    buildToolsVersion = "36.0.0"
    defaultConfig {
        applicationId = "com.github.xnovo3000.gigapass"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
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
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
}

dependencies {
    // Project
    api(project(":feature:key"))
    api(project(":feature:keychain"))
    // Project - Baseline
    implementation(libs.androidx.profileinstaller)
    baselineProfile(project(":baseline"))
    // AndroidX
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity)
    // Jetpack Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.animation)
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    // Lifecycle
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.navigation3)
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    // KotlinX
    implementation(libs.kotlinx.serialization.core)
    // Navigation3
    implementation(libs.navigation3.runtime)
    implementation(libs.navigation3.ui)
}