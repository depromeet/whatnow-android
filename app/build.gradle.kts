plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.secrets.gradle.plugin)
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.depromeet.whatnow"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.depromeet.whatnow"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.COMPILE_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/INDEX.LIST"
        }
    }
}

dependencies {
    implementation("com.kakao.sdk:v2-all:2.14.0")
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.DATA))
    implementation(libs.google.hilt.android)
    kapt(libs.google.hilt.compiler)
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.okhttp.logging)
}
