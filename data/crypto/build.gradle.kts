plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    kotlin("kapt")
}

android {
    namespace = "com.github.xnovo3000.gigapass.data.crypto"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidx.core)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)
    // JDK 11 desugaring
    coreLibraryDesugaring(libs.jdk.desugar)
    // Test - Integration
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.rules)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.ext.truth)
}

kapt {
    correctErrorTypes = true
}