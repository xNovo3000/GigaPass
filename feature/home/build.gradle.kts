plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.github.xnovo3000.gigapass.feature.home"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = true
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "${libs.versions.jetpack.compose.compiler}"
    }
}

dependencies {
    // Project
    api(project(":core:algorithm"))
    api(project(":core:ui"))
    api(project(":data:password"))
    // AndroidX
    implementation(libs.androidx.core)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)
    implementation(libs.dagger.hilt.navigation.compose)  // Compose add-on
    // Jetpack Compose
    implementation(platform(libs.jetpack.compose.bom))
    implementation(libs.jetpack.compose.bom)
    implementation(libs.jetpack.compose.animation)
    implementation(libs.jetpack.compose.foundation)
    implementation(libs.jetpack.compose.runtime)
    implementation(libs.jetpack.compose.ui)
    implementation(libs.jetpack.compose.compiler)
    // Jetpack Compose - Material 3
    implementation(libs.jetpack.compose.material3)
    // Jetpack Compose - Navigation
    implementation(libs.navigation.compose)
    // Jetpack Compose - Preview
    debugImplementation(libs.jetpack.compose.ui.tooling)
    implementation(libs.jetpack.compose.ui.tooling.preview)
    // JDK 11 desugaring
    coreLibraryDesugaring(libs.jdk.desugar)
}

kapt {
    correctErrorTypes = true
}