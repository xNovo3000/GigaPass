plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.github.xnovo3000.gigapass.core.ui"
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
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidx.core)
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
    // JDK 11 desugaring
    coreLibraryDesugaring(libs.jdk.desugar)
}