plugins {
    id("com.android.library")
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
    implementation(libs.activity.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.androidx.viewmodel.compose)
    implementation(libs.androidx.splashscreen)

    implementation(libs.compose.ui.core)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)

    implementation(libs.coil.compose)
    implementation(libs.image.cropper)
    
    implementation(libs.google.hilt.android)

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(libs.naver.map)
    implementation(libs.naver.map.compose)

    kapt(libs.google.hilt.compiler)

    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.google.accompanist.systemuicontroller)
    implementation(libs.google.accompanist.navigation.animation)
    implementation(libs.google.accompanist.webview)
    implementation(libs.google.accompanist.pager)
    implementation(libs.google.accompanist.pager.indicators)

    implementation(libs.calendar.compose)

    coreLibraryDesugaring(libs.android.tools.desugar)


    //  Camerax dependencies
    implementation("androidx.camera:camera-core:1.2.3")
    implementation("androidx.camera:camera-camera2:1.2.3")
    implementation("androidx.camera:camera-lifecycle:1.2.3")
    implementation("androidx.camera:camera-view:1.3.0-beta01")
    implementation("androidx.camera:camera-extensions:1.3.0-beta01")
    implementation("com.kakao.sdk:v2-all:2.14.0")


    //permission handling in compose
    implementation("com.google.accompanist:accompanist-permissions:0.30.0")
}
