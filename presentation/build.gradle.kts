plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.depromeet.whatnow.ui"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.androidx.viewmodel.compose)
    implementation(libs.androidx.splashscreen)

    implementation(libs.compose.ui.core)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)

    implementation(libs.coil.compose)

    implementation(libs.google.hilt.android)
    implementation(libs.naver.map.compose)
    kapt(libs.google.hilt.compiler)

    debugImplementation(libs.compose.ui.tooling)
}
