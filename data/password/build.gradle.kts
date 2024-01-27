plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    kotlin("kapt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.github.xnovo3000.gigapass.data.password"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
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
    // Room
    implementation(libs.room)
    ksp(libs.room.compiler)
    // JDK 11 desugaring
    coreLibraryDesugaring(libs.jdk.desugar)
}

kapt {
    correctErrorTypes = true
}