buildscript {
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath("com.google.gms:google-services:4.3.15")
    }
}

plugins {
    alias(libs.plugins.com.android.application) apply (false)
    alias(libs.plugins.org.jetbrains.kotlin.android) apply (false)
    alias(libs.plugins.com.google.hilt.android) apply (false)
    alias(libs.plugins.secrets.gradle.plugin) apply (false)
}
